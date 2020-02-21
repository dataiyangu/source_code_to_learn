/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.collector.handler.thrift;

import com.navercorp.pinpoint.collector.handler.SimpleHandler;
import com.navercorp.pinpoint.collector.mapper.thrift.event.ThriftAgentEventBatchMapper;
import com.navercorp.pinpoint.collector.mapper.thrift.event.ThriftAgentEventMapper;
import com.navercorp.pinpoint.collector.service.AgentEventService;
import com.navercorp.pinpoint.common.server.bo.event.AgentEventBo;
import com.navercorp.pinpoint.common.server.bo.event.DeadlockEventBo;
import com.navercorp.pinpoint.common.server.util.AgentEventMessageSerializerV1;
import com.navercorp.pinpoint.common.util.CollectionUtils;
import com.navercorp.pinpoint.io.request.ServerRequest;
import com.navercorp.pinpoint.thrift.dto.TAgentStat;
import com.navercorp.pinpoint.thrift.dto.TAgentStatBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Taejin Koo
 * @author jaehong.kim - Add AgentEventMessageSerializerV1
 */
@Service
public class ThriftAgentEventHandler implements SimpleHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ThriftAgentEventMapper agentEventMapper;

    @Autowired
    private ThriftAgentEventBatchMapper agentEventBatchMapper;

    @Autowired
    private AgentEventMessageSerializerV1 agentEventMessageSerializerV1;

    @Autowired
    private AgentEventService agentEventService;

    @Override
    public void handleSimple(ServerRequest serverRequest) {
        final Object data = serverRequest.getData();
        if (logger.isDebugEnabled()) {
            logger.debug("Handle simple data={}", data);
        }

        if (data instanceof TAgentStat) {
            handleAgentStat((TAgentStat) data);
        } else if (data instanceof TAgentStatBatch) {
            handleAgentStatBatch((TAgentStatBatch) data);
        } else {
            throw new UnsupportedOperationException("data is not support type : " + data);
        }
    }

    private void handleAgentStat(TAgentStat agentStat) {
        final AgentEventBo agentEventBo = this.agentEventMapper.map(agentStat);
        if (agentEventBo == null) {
            return;
        }
        insert(agentEventBo);
    }

    private void handleAgentStatBatch(TAgentStatBatch tAgentStatBatch) {
        final List<AgentEventBo> agentEventBoList = this.agentEventBatchMapper.map(tAgentStatBatch);
        if (CollectionUtils.isEmpty(agentEventBoList)) {
            return;
        }

        for (AgentEventBo agentEventBo : agentEventBoList) {
            insert(agentEventBo);
        }
    }

    private void insert(final AgentEventBo agentEventBo) {
        try {
            final Object eventMessage = getEventMessage(agentEventBo);
            final byte[] eventBody = agentEventMessageSerializerV1.serialize(agentEventBo.getEventType(), eventMessage);
            agentEventBo.setEventBody(eventBody);
        } catch (Exception e) {
            logger.warn("error handling agent event", e);
            return;
        }
        this.agentEventService.insert(agentEventBo);
    }

    private Object getEventMessage(AgentEventBo agentEventBo) {
        if (agentEventBo instanceof DeadlockEventBo) {
            return ((DeadlockEventBo) agentEventBo).getDeadlockBo();
        }
        throw new IllegalArgumentException("unsupported message " + agentEventBo);
    }
}