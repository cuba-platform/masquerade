/*
 * Copyright (c) 2008-2017 Haulmont.
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

package com.haulmont.masquerade.sys.matchers;

import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.matchers.ArgumentMatchers;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;

import java.util.List;

public final class InstanceOfCases {
    private InstanceOfCases() {
    }

    public static <T, C> DecomposableMatchBuilder1<T, C> hasType(Class<C> clazz) {
        List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
        return new DecomposableMatchBuilder1<>(matchers, 0, new InstanceOfFieldExtractor<>(clazz));
    }
}