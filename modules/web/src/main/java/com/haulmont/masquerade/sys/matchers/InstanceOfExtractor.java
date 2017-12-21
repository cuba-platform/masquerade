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

import com.leacox.motif.extract.Extractor1;

import java.util.Optional;

public class InstanceOfExtractor<T, C> implements Extractor1<T, C> {
    private Class<C> clazz;

    public InstanceOfExtractor(Class<C> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<C> unapply(T t) {
        if (t != null && clazz.isAssignableFrom(t.getClass())) {
            return Optional.of(clazz.cast(t));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Class getExtractorClass() {
        return clazz;
    }
}