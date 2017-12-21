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

package com.haulmont.masquerade.conditions;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;

/**
 * Implementation depends on Component.
 */
public class SpecificCondition extends Condition {
    public SpecificCondition(String name) {
        super(name);
    }

    @Override
    public boolean apply(WebElement element) {
        SpecificConditionHandler handler = SpecificConditionContext.getHandler();
        if (handler == null) {
            throw new RuntimeException(
                    "SpecificCondition must be checked ony in SpecificConditionHandler implementations");
        }

        return handler.apply(this);
    }
}