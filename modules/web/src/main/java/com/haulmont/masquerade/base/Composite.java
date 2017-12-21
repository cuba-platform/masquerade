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

package com.haulmont.masquerade.base;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.components.Container;
import org.openqa.selenium.By;

/**
 * Convenient parent class for composite UI components: panels, screens, tabs, etc.
 *
 * @param <T> type of class
 */
public abstract class Composite<T> implements Container<T> {
    @Wire
    private SelenideElement impl;
    @Wire
    private By by;

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public By getBy() {
        return by;
    }

    public <X> X actAs(Class<X> clazz) {
        return Components.wire(clazz, by);
    }
}