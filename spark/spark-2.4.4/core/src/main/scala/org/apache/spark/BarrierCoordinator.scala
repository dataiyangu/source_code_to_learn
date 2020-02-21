/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark

import java.util.{Timer, TimerTask}
import java.util.concurrent.ConcurrentHashMap
import java.util.function.{Consumer, Function}

import scala.collection.mutable.ArrayBuffer

import org.apache.spark.internal.Logging
import org.apache.spark.rpc.{RpcCallContext, RpcEnv, ThreadSafeRpcEndpoint}
import org.apache.spark.scheduler.{LiveListenerBus, SparkListener, SparkListenerStageCompleted}

/**
 * For each barrier stage attempt, only at most one barrier() call can be active at any time, thus
 * we can use (stageId, stageAttemptId) to identify the stage attempt where the barrier() call is
 * from.
 */
private case class ContextBarrierId(stageId: Int, stageAttemptId: Int) {
  override def toString: String = s"Stage $stageId (Attempt $stageAttemptId)"
}

/**
 * A coordinator that handles all global sync requests from BarrierTaskContext. Each global sync
 * request is generated by `BarrierTaskContext.barrier()`, and identified by
 * stageId + stageAttemptId + barrierEpoch. Reply all the blocking global sync requests upon
 * all the requests for a group of `barrier()` calls are received. If the coordinator is unable to
 * collect enough global sync requests within a configured time, fail all the requests and return
 * an Exception with timeout message.
 */
private[spark] class BarrierCoordinator(
    timeoutInSecs: Long,
    listenerBus: LiveListenerBus,
    override val rpcEnv: RpcEnv) extends ThreadSafeRpcEndpoint with Logging {

  // TODO SPARK-25030 Create a Timer() in the mainClass submitted to SparkSubmit makes it unable to
  // fetch result, we shall fix the issue.
  private lazy val timer = new Timer("BarrierCoordinator barrier epoch increment timer")

  // Listen to StageCompleted event, clear corresponding ContextBarrierState.
  private val listener = new SparkListener {
    override def onStageCompleted(stageCompleted: SparkListenerStageCompleted): Unit = {
      val stageInfo = stageCompleted.stageInfo
      val barrierId = ContextBarrierId(stageInfo.stageId, stageInfo.attemptNumber)
      // Clear ContextBarrierState from a finished stage attempt.
      cleanupBarrierStage(barrierId)
    }
  }

  // Record all active stage attempts that make barrier() call(s), and the corresponding internal
  // state.
  private val states = new ConcurrentHashMap[ContextBarrierId, ContextBarrierState]

  override def onStart(): Unit = {
    super.onStart()
    listenerBus.addToStatusQueue(listener)
  }

  override def onStop(): Unit = {
    try {
      states.forEachValue(1, clearStateConsumer)
      states.clear()
      listenerBus.removeListener(listener)
    } finally {
      super.onStop()
    }
  }

  /**
   * Provide the current state of a barrier() call. A state is created when a new stage attempt
   * sends out a barrier() call, and recycled on stage completed.
   *
   * @param barrierId Identifier of the barrier stage that make a barrier() call.
   * @param numTasks Number of tasks of the barrier stage, all barrier() calls from the stage shall
   *                 collect `numTasks` requests to succeed.
   */
  private class ContextBarrierState(
      val barrierId: ContextBarrierId,
      val numTasks: Int) {

    // There may be multiple barrier() calls from a barrier stage attempt, `barrierEpoch` is used
    // to identify each barrier() call. It shall get increased when a barrier() call succeeds, or
    // reset when a barrier() call fails due to timeout.
    private var barrierEpoch: Int = 0

    // An array of RPCCallContexts for barrier tasks that are waiting for reply of a barrier()
    // call.
    private val requesters: ArrayBuffer[RpcCallContext] = new ArrayBuffer[RpcCallContext](numTasks)

    // A timer task that ensures we may timeout for a barrier() call.
    private var timerTask: TimerTask = null

    // Init a TimerTask for a barrier() call.
    private def initTimerTask(): Unit = {
      timerTask = new TimerTask {
        override def run(): Unit = synchronized {
          // Timeout current barrier() call, fail all the sync requests.
          requesters.foreach(_.sendFailure(new SparkException("The coordinator didn't get all " +
            s"barrier sync requests for barrier epoch $barrierEpoch from $barrierId within " +
            s"$timeoutInSecs second(s).")))
          cleanupBarrierStage(barrierId)
        }
      }
    }

    // Cancel the current active TimerTask and release resources.
    private def cancelTimerTask(): Unit = {
      if (timerTask != null) {
        timerTask.cancel()
        timer.purge()
        timerTask = null
      }
    }

    // Process the global sync request. The barrier() call succeed if collected enough requests
    // within a configured time, otherwise fail all the pending requests.
    def handleRequest(requester: RpcCallContext, request: RequestToSync): Unit = synchronized {
      val taskId = request.taskAttemptId
      val epoch = request.barrierEpoch

      // Require the number of tasks is correctly set from the BarrierTaskContext.
      require(request.numTasks == numTasks, s"Number of tasks of $barrierId is " +
        s"${request.numTasks} from Task $taskId, previously it was $numTasks.")

      // Check whether the epoch from the barrier tasks matches current barrierEpoch.
      logInfo(s"Current barrier epoch for $barrierId is $barrierEpoch.")
      if (epoch != barrierEpoch) {
        requester.sendFailure(new SparkException(s"The request to sync of $barrierId with " +
          s"barrier epoch $barrierEpoch has already finished. Maybe task $taskId is not " +
          "properly killed."))
      } else {
        // If this is the first sync message received for a barrier() call, start timer to ensure
        // we may timeout for the sync.
        if (requesters.isEmpty) {
          initTimerTask()
          timer.schedule(timerTask, timeoutInSecs * 1000)
        }
        // Add the requester to array of RPCCallContexts pending for reply.
        requesters += requester
        logInfo(s"Barrier sync epoch $barrierEpoch from $barrierId received update from Task " +
          s"$taskId, current progress: ${requesters.size}/$numTasks.")
        if (maybeFinishAllRequesters(requesters, numTasks)) {
          // Finished current barrier() call successfully, clean up ContextBarrierState and
          // increase the barrier epoch.
          logInfo(s"Barrier sync epoch $barrierEpoch from $barrierId received all updates from " +
            s"tasks, finished successfully.")
          barrierEpoch += 1
          requesters.clear()
          cancelTimerTask()
        }
      }
    }

    // Finish all the blocking barrier sync requests from a stage attempt successfully if we
    // have received all the sync requests.
    private def maybeFinishAllRequesters(
        requesters: ArrayBuffer[RpcCallContext],
        numTasks: Int): Boolean = {
      if (requesters.size == numTasks) {
        requesters.foreach(_.reply(()))
        true
      } else {
        false
      }
    }

    // Cleanup the internal state of a barrier stage attempt.
    def clear(): Unit = synchronized {
      // The global sync fails so the stage is expected to retry another attempt, all sync
      // messages come from current stage attempt shall fail.
      barrierEpoch = -1
      requesters.clear()
      cancelTimerTask()
    }
  }

  // Clean up the [[ContextBarrierState]] that correspond to a specific stage attempt.
  private def cleanupBarrierStage(barrierId: ContextBarrierId): Unit = {
    val barrierState = states.remove(barrierId)
    if (barrierState != null) {
      barrierState.clear()
    }
  }

  override def receiveAndReply(context: RpcCallContext): PartialFunction[Any, Unit] = {
    case request @ RequestToSync(numTasks, stageId, stageAttemptId, _, _) =>
      // Get or init the ContextBarrierState correspond to the stage attempt.
      val barrierId = ContextBarrierId(stageId, stageAttemptId)
      states.computeIfAbsent(barrierId, new Function[ContextBarrierId, ContextBarrierState] {
        override def apply(key: ContextBarrierId): ContextBarrierState =
          new ContextBarrierState(key, numTasks)
      })
      val barrierState = states.get(barrierId)

      barrierState.handleRequest(context, request)
  }

  private val clearStateConsumer = new Consumer[ContextBarrierState] {
    override def accept(state: ContextBarrierState) = state.clear()
  }
}

private[spark] sealed trait BarrierCoordinatorMessage extends Serializable

/**
 * A global sync request message from BarrierTaskContext, by `barrier()` call. Each request is
 * identified by stageId + stageAttemptId + barrierEpoch.
 *
 * @param numTasks The number of global sync requests the BarrierCoordinator shall receive
 * @param stageId ID of current stage
 * @param stageAttemptId ID of current stage attempt
 * @param taskAttemptId Unique ID of current task
 * @param barrierEpoch ID of the `barrier()` call, a task may consist multiple `barrier()` calls.
 */
private[spark] case class RequestToSync(
    numTasks: Int,
    stageId: Int,
    stageAttemptId: Int,
    taskAttemptId: Long,
    barrierEpoch: Int) extends BarrierCoordinatorMessage
