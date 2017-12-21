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

package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.TimeField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;

public class TimeFieldImpl extends AbstractInputComponent<TimeField> implements TimeField {
    public TimeFieldImpl(By by) {
        super(by);
    }

    @Override
    protected SelenideElement getInputDelegate() {
        return impl;
    }

    @Override
    public String getValue() {
        return impl
                .shouldBe(visible)
                .getValue();
    }

    @Override
    public TimeField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .click();

        impl.sendKeys(Keys.HOME, value);
        return this;
    }
}