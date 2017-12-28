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
import org.openqa.selenium.By;

import java.util.List;

import static com.haulmont.masquerade.Selectors.byCubaId;

/**
 * Button component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#VISIBLE}</li>
 *     <li>{@link Conditions#HIDDEN}</li>
 *     <li>{@link Conditions#ENABLED}</li>
 *     <li>{@link Conditions#DISABLED}</li>
 * </ul>
 */
public interface TabSheet extends Container<TabSheet> {
    Tab getTab(By tabBy);

    default Tab getTab(String cubaId) {
        return getTab(byCubaId(cubaId));
    }

    List<Tab> getVisibleTabs();

    /**
     * Tab header element.
     * <br>
     * Supported conditions:
     * <ul>
     *     <li>{@link Conditions#VISIBLE}</li>
     *     <li>{@link Conditions#HIDDEN}</li>
     *     <li>{@link Conditions#SELECTED}</li>
     *     <li>{@link Conditions#caption(String)} </li>
     *     <li>{@link Conditions#captionContains(String)} </li>
     * </ul>
     */
    interface Tab extends SelenideElementWrapper<Tab>, ByLocator, Element {
        @Log
        void select();

        @Log
        void close();
    }
}