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

package org.apache.spark.streaming.receiver

import java.util.concurrent.{ArrayBlockingQueue, TimeUnit}

import scala.collection.mutable.ArrayBuffer

import org.apache.spark.{SparkConf, SparkException}
import org.apache.spark.internal.Logging
import org.apache.spark.storage.StreamBlockId
import org.apache.spark.streaming.util.RecurringTimer
import org.apache.spark.util.{Clock, SystemClock}

/** Listener object for BlockGenerator events */
private[streaming] trait BlockGeneratorListener {
  /**
   * Called after a data item is added into the BlockGenerator. The data addition and this
   * callback are synchronized with the block generation and its associated callback,
   * so block generation waits for the active data addition+callback to complete. This is useful
   * for updating metadata on successful buffering of a data item, specifically that metadata
   * that will be useful when a block is generated. Any long blocking operation in this callback
   * will hurt the throughput.
   */
  def onAddData(data: Any, metadata: Any): Unit

  /**
   * Called when a new block of data is generated by the block generator. The block generation
   * and this callback are synchronized with the data addition and its associated callback, so
   * the data addition waits for the block generation+callback to complete. This is useful
   * for updating metadata when a block has been generated, specifically metadata that will
   * be useful when the block has been successfully stored. Any long blocking operation in this
   * callback will hurt the throughput.
   */
  def onGenerateBlock(blockId: StreamBlockId): Unit

  /**
   * Called when a new block is ready to be pushed. Callers are supposed to store the block into
   * Spark in this method. Internally this is called from a single
   * thread, that is not synchronized with any other callbacks. Hence it is okay to do long
   * blocking operation in this callback.
   */
  def onPushBlock(blockId: StreamBlockId, arrayBuffer: ArrayBuffer[_]): Unit

  /**
   * Called when an error has occurred in the BlockGenerator. Can be called form many places
   * so better to not do any long block operation in this callback.
   */
  def onError(message: String, throwable: Throwable): Unit
}

/**
 * Generates batches of objects received by a
 * [[org.apache.spark.streaming.receiver.Receiver]] and puts them into appropriately
 * named blocks at regular intervals. This class starts two threads,
 * one to periodically start a new batch and prepare the previous batch of as a block,
 * the other to push the blocks into the block manager.
 *
 * Note: Do not create BlockGenerator instances directly inside receivers. Use
 * `ReceiverSupervisor.createBlockGenerator` to create a BlockGenerator and use it.
 */
private[streaming] class BlockGenerator(
    listener: BlockGeneratorListener,
    receiverId: Int,
    conf: SparkConf,
    clock: Clock = new SystemClock()
  ) extends RateLimiter(conf) with Logging {

  private case class Block(id: StreamBlockId, buffer: ArrayBuffer[Any])

  /**
   * The BlockGenerator can be in 5 possible states, in the order as follows.
   *
   *  - Initialized: Nothing has been started.
   *  - Active: start() has been called, and it is generating blocks on added data.
   *  - StoppedAddingData: stop() has been called, the adding of data has been stopped,
   *                       but blocks are still being generated and pushed.
   *  - StoppedGeneratingBlocks: Generating of blocks has been stopped, but
   *                             they are still being pushed.
   *  - StoppedAll: Everything has been stopped, and the BlockGenerator object can be GCed.
   */
  private object GeneratorState extends Enumeration {
    type GeneratorState = Value
    val Initialized, Active, StoppedAddingData, StoppedGeneratingBlocks, StoppedAll = Value
  }
  import GeneratorState._

  private val blockIntervalMs = conf.getTimeAsMs("spark.streaming.blockInterval", "200ms")
  require(blockIntervalMs > 0, s"'spark.streaming.blockInterval' should be a positive value")

  private val blockIntervalTimer =
    new RecurringTimer(clock, blockIntervalMs, updateCurrentBuffer, "BlockGenerator")
  private val blockQueueSize = conf.getInt("spark.streaming.blockQueueSize", 10)
  private val blocksForPushing = new ArrayBlockingQueue[Block](blockQueueSize)
  private val blockPushingThread = new Thread() { override def run() { keepPushingBlocks() } }

  @volatile private var currentBuffer = new ArrayBuffer[Any]
  @volatile private var state = Initialized

  /** Start block generating and pushing threads. */
  def start(): Unit = synchronized {
    if (state == Initialized) {
      state = Active
      blockIntervalTimer.start()
      blockPushingThread.start()
      logInfo("Started BlockGenerator")
    } else {
      throw new SparkException(
        s"Cannot start BlockGenerator as its not in the Initialized state [state = $state]")
    }
  }

  /**
   * Stop everything in the right order such that all the data added is pushed out correctly.
   *
   *  - First, stop adding data to the current buffer.
   *  - Second, stop generating blocks.
   *  - Finally, wait for queue of to-be-pushed blocks to be drained.
   */
  def stop(): Unit = {
    // Set the state to stop adding data
    synchronized {
      if (state == Active) {
        state = StoppedAddingData
      } else {
        logWarning(s"Cannot stop BlockGenerator as its not in the Active state [state = $state]")
        return
      }
    }

    // Stop generating blocks and set the state for block pushing thread to start draining the queue
    logInfo("Stopping BlockGenerator")
    blockIntervalTimer.stop(interruptTimer = false)
    synchronized { state = StoppedGeneratingBlocks }

    // Wait for the queue to drain and mark state as StoppedAll
    logInfo("Waiting for block pushing thread to terminate")
    blockPushingThread.join()
    synchronized { state = StoppedAll }
    logInfo("Stopped BlockGenerator")
  }

  /**
   * Push a single data item into the buffer.
   */
  def addData(data: Any): Unit = {
    if (state == Active) {
      waitToPush()
      synchronized {
        if (state == Active) {
          currentBuffer += data
        } else {
          throw new SparkException(
            "Cannot add data as BlockGenerator has not been started or has been stopped")
        }
      }
    } else {
      throw new SparkException(
        "Cannot add data as BlockGenerator has not been started or has been stopped")
    }
  }

  /**
   * Push a single data item into the buffer. After buffering the data, the
   * `BlockGeneratorListener.onAddData` callback will be called.
   */
  def addDataWithCallback(data: Any, metadata: Any): Unit = {
    if (state == Active) {
      waitToPush()
      synchronized {
        if (state == Active) {
          currentBuffer += data
          listener.onAddData(data, metadata)
        } else {
          throw new SparkException(
            "Cannot add data as BlockGenerator has not been started or has been stopped")
        }
      }
    } else {
      throw new SparkException(
        "Cannot add data as BlockGenerator has not been started or has been stopped")
    }
  }

  /**
   * Push multiple data items into the buffer. After buffering the data, the
   * `BlockGeneratorListener.onAddData` callback will be called. Note that all the data items
   * are atomically added to the buffer, and are hence guaranteed to be present in a single block.
   */
  def addMultipleDataWithCallback(dataIterator: Iterator[Any], metadata: Any): Unit = {
    if (state == Active) {
      // Unroll iterator into a temp buffer, and wait for pushing in the process
      val tempBuffer = new ArrayBuffer[Any]
      dataIterator.foreach { data =>
        waitToPush()
        tempBuffer += data
      }
      synchronized {
        if (state == Active) {
          currentBuffer ++= tempBuffer
          listener.onAddData(tempBuffer, metadata)
        } else {
          throw new SparkException(
            "Cannot add data as BlockGenerator has not been started or has been stopped")
        }
      }
    } else {
      throw new SparkException(
        "Cannot add data as BlockGenerator has not been started or has been stopped")
    }
  }

  def isActive(): Boolean = state == Active

  def isStopped(): Boolean = state == StoppedAll

  /** Change the buffer to which single records are added to. */
  private def updateCurrentBuffer(time: Long): Unit = {
    try {
      var newBlock: Block = null
      synchronized {
        if (currentBuffer.nonEmpty) {
          val newBlockBuffer = currentBuffer
          currentBuffer = new ArrayBuffer[Any]
          val blockId = StreamBlockId(receiverId, time - blockIntervalMs)
          listener.onGenerateBlock(blockId)
          newBlock = new Block(blockId, newBlockBuffer)
        }
      }

      if (newBlock != null) {
        blocksForPushing.put(newBlock)  // put is blocking when queue is full
      }
    } catch {
      case ie: InterruptedException =>
        logInfo("Block updating timer thread was interrupted")
      case e: Exception =>
        reportError("Error in block updating thread", e)
    }
  }

  /** Keep pushing blocks to the BlockManager. */
  private def keepPushingBlocks() {
    logInfo("Started block pushing thread")

    def areBlocksBeingGenerated: Boolean = synchronized {
      state != StoppedGeneratingBlocks
    }

    try {
      // While blocks are being generated, keep polling for to-be-pushed blocks and push them.
      while (areBlocksBeingGenerated) {
        Option(blocksForPushing.poll(10, TimeUnit.MILLISECONDS)) match {
          case Some(block) => pushBlock(block)
          case None =>
        }
      }

      // At this point, state is StoppedGeneratingBlock. So drain the queue of to-be-pushed blocks.
      logInfo("Pushing out the last " + blocksForPushing.size() + " blocks")
      while (!blocksForPushing.isEmpty) {
        val block = blocksForPushing.take()
        logDebug(s"Pushing block $block")
        pushBlock(block)
        logInfo("Blocks left to push " + blocksForPushing.size())
      }
      logInfo("Stopped block pushing thread")
    } catch {
      case ie: InterruptedException =>
        logInfo("Block pushing thread was interrupted")
      case e: Exception =>
        reportError("Error in block pushing thread", e)
    }
  }

  private def reportError(message: String, t: Throwable) {
    logError(message, t)
    listener.onError(message, t)
  }

  private def pushBlock(block: Block) {
    listener.onPushBlock(block.id, block.buffer)
    logInfo("Pushed block " + block.id)
  }
}
