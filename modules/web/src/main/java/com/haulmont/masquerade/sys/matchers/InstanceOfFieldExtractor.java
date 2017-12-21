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

import com.leacox.motif.extract.FieldExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstanceOfFieldExtractor<T, C> implements FieldExtractor<T> {
    private final InstanceOfExtractor<T, C> extractor;

    public InstanceOfFieldExtractor(Class<C> clazz) {
        this.extractor = new InstanceOfExtractor<>(clazz);
    }

    @Override
    public Optional<List<Object>> unapply(T value) {
        Optional<C> opt = extractor.unapply(value);
        if (!opt.isPresent()) {
            return Optional.empty();
        }

        List<Object> fields = new ArrayList<>();
        fields.add(opt.get());

        return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
        return extractor.getExtractorClass();
    }
}