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

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;
import com.haulmont.masquerade.util.Log;

import java.util.List;

public interface HasOptionsPopup<T> {
    OptionsPopup<T> getOptionsPopup();
    @Log
    OptionsPopup<T> openOptionsPopup();
    @Log
    T closeOptionsPopup();

    /**
     * Options popup.
     * <br>
     * Supported conditions:
     * <ul>
     *     <li>{@link Conditions#VISIBLE}</li>
     *     <li>{@link Conditions#HIDDEN}</li>
     *     <li>{@link Conditions#visibleOptions(String...)}</li>
     *     <li>{@link Conditions#visibleOptionsCount(int)}</li>
     * </ul>
     */
    interface OptionsPopup<T> extends SelenideElementWrapper<OptionsPopup>, ByLocator, Element {
        List<String> getVisibleOptions();

        @Log
        T select(String option);

        @Log
        OptionsPopup<T> nextPage();
        @Log
        OptionsPopup<T> previousPage();

        boolean hasNextPage();
    }
}