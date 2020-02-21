/*
 *  Copyright 2018 NAVER Corp.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.navercorp.pinpoint.plugin.elasticsearchbboss.interceptor;

import com.navercorp.pinpoint.bootstrap.async.AsyncContextAccessor;
import com.navercorp.pinpoint.bootstrap.context.AsyncContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.IgnoreMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.InterceptorScope;

/**
 * @author yinbp[yin-bp@163.com]
 */
public class ParallelWorkerConstructorInterceptor implements AroundInterceptor {
	private final InterceptorScope scope;

	public ParallelWorkerConstructorInterceptor(InterceptorScope scope) {
		this.scope = scope;
	}

	@IgnoreMethod
	public void before(Object target, Object arg[]) {

	}


	public void after(Object target, Object arg[], Object result, Throwable throwable) {
		AsyncContext asyncContext = (AsyncContext) scope.getCurrentInvocation().getAttachment();
		((AsyncContextAccessor) target)._$PINPOINT$_setAsyncContext(asyncContext);
	}
}
