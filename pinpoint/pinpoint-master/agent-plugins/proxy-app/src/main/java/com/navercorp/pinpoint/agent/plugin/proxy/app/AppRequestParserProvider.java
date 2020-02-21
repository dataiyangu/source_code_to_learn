/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.agent.plugin.proxy.app;

import com.navercorp.pinpoint.profiler.context.recorder.proxy.ProxyRequestParser;
import com.navercorp.pinpoint.profiler.context.recorder.proxy.ProxyRequestParserProvider;
import com.navercorp.pinpoint.profiler.context.recorder.proxy.ProxyRequestParserProviderSetupContext;

/**
 * @author jaehong.kim
 */
public class AppRequestParserProvider implements ProxyRequestParserProvider {
    @Override
    public void setup(ProxyRequestParserProviderSetupContext context) {
        final ProxyRequestParser parser = new AppRequestParser();
        context.addProxyRequestParser(parser);
    }
}