/*
 * Copyright 2014 NAVER Corp.
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
package com.navercorp.pinpoint.test.plugin;

/**
 * @author Jongho Moon
 *
 */
@SuppressWarnings("serial")
public class PinpointPluginTestException extends RuntimeException {
    public PinpointPluginTestException(String message, StackTraceElement[] stackTrace, Throwable cause) {
        super(message, cause);
        setStackTrace(stackTrace);
    }

    public PinpointPluginTestException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }

    public PinpointPluginTestException() {
        super();
    }

    public PinpointPluginTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinpointPluginTestException(String message) {
        super(message);
    }

    public PinpointPluginTestException(Throwable cause) {
        super(cause);
    }
    
    
}
