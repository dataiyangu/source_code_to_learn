/*
 * Copyright 2014 NAVER Corp.
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

package com.navercorp.pinpoint.profiler.util;

import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentMap;

/**
 * @author emeroad
 */
public final class Maps {

    private static MapMaker createWeakMapMaker() {
        final MapMaker mapMaker = new MapMaker();
        mapMaker.weakKeys();
        return mapMaker;
    }

    public static <K, V> ConcurrentMap<K, V> newWeakConcurrentMap() {
        MapMaker weakMapMaker = createWeakMapMaker();
        return weakMapMaker.makeMap();
    }

    public static <K, V> ConcurrentMap<K, V> newWeakConcurrentMap(int initialCapacity) {
        final MapMaker weakMapMaker = createWeakMapMaker();
        weakMapMaker.initialCapacity(initialCapacity);
        return weakMapMaker.makeMap();
    }
}
