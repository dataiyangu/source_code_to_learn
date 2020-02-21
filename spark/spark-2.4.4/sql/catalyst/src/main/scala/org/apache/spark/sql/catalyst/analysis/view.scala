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

package org.apache.spark.sql.catalyst.analysis

import org.apache.spark.sql.catalyst.expressions.Alias
import org.apache.spark.sql.catalyst.plans.logical.{LogicalPlan, Project, View}
import org.apache.spark.sql.catalyst.rules.Rule
import org.apache.spark.sql.internal.SQLConf

/**
 * This file defines analysis rules related to views.
 */

/**
 * This rule has two goals:
 *
 * 1. Removes [[View]] operators from the plan. The operator is respected till the end of analysis
 * stage because we want to see which part of an analyzed logical plan is generated from a view.
 *
 * 2. Make sure that a view's child plan produces the view's output attributes. We try to wrap the
 * child by:
 * 1. Generate the `queryOutput` by:
 *    1.1. If the query column names are defined, map the column names to attributes in the child
 *         output by name(This is mostly for handling view queries like SELECT * FROM ..., the
 *         schema of the referenced table/view may change after the view has been created, so we
 *         have to save the output of the query to `viewQueryColumnNames`, and restore them during
 *         view resolution, in this way, we are able to get the correct view column ordering and
 *         omit the extra columns that we don't require);
 *    1.2. Else set the child output attributes to `queryOutput`.
 * 2. Map the `queryOutput` to view output by index, if the corresponding attributes don't match,
 *    try to up cast and alias the attribute in `queryOutput` to the attribute in the view output.
 * 3. Add a Project over the child, with the new output generated by the previous steps.
 *
 * Once reaches this rule, it means `CheckAnalysis` did necessary checks on number of columns
 * between the view output and the child output or the query column names. `CheckAnalysis` also
 * checked the cast from the view's child to the Project is up-cast.
 *
 * This should be only done after the batch of Resolution, because the view attributes are not
 * completely resolved during the batch of Resolution.
 */
object EliminateView extends Rule[LogicalPlan] with CastSupport {
  override def conf: SQLConf = SQLConf.get

  override def apply(plan: LogicalPlan): LogicalPlan = plan transformUp {
    // The child has the different output attributes with the View operator. Adds a Project over
    // the child of the view.
    case v @ View(desc, output, child) if child.resolved && output != child.output =>
      val resolver = conf.resolver
      val queryColumnNames = desc.viewQueryColumnNames
      val queryOutput = if (queryColumnNames.nonEmpty) {
        // Find the attribute that has the expected attribute name from an attribute list, the names
        // are compared using conf.resolver.
        // `CheckAnalysis` already guarantees the expected attribute can be found for sure.
        desc.viewQueryColumnNames.map { colName =>
          child.output.find(attr => resolver(attr.name, colName)).get
        }
      } else {
        // For view created before Spark 2.2.0, the view text is already fully qualified, the plan
        // output is the same with the view output.
        child.output
      }
      // Map the attributes in the query output to the attributes in the view output by index.
      val newOutput = output.zip(queryOutput).map {
        case (attr, originAttr) if !attr.semanticEquals(originAttr) =>
          // `CheckAnalysis` already guarantees that the cast is a up-cast for sure.
          Alias(cast(originAttr, attr.dataType), attr.name)(exprId = attr.exprId,
            qualifier = attr.qualifier, explicitMetadata = Some(attr.metadata))
        case (_, originAttr) => originAttr
      }
      Project(newOutput, child)

    // The child should have the same output attributes with the View operator, so we simply
    // remove the View operator.
    case View(_, _, child) =>
      child
  }
}
