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

package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Components;
import org.openqa.selenium.By;

import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byPath;

public interface Container<T> extends Component<T> {
    default <C> C child(Class<C> childClazz, String... childPath) {
        return child(childClazz, byPath(childPath));
    }

    default <C> C child(Class<C> childClazz, By childBy) {
        return Components.wire(childClazz, byChain(getBy(), childBy));
    }
}