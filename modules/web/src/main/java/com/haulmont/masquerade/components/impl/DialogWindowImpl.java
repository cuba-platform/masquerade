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
import com.haulmont.masquerade.components.DialogWindow;
import com.haulmont.masquerade.conditions.Caption;
import com.haulmont.masquerade.conditions.CaptionContains;
import com.haulmont.masquerade.conditions.SpecificCondition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.className;

public class DialogWindowImpl extends AbstractComponent<DialogWindow> implements DialogWindow {

    public static final By CLOSE_BUTTON = className("v-window-closebox");
    public static final By HEADER = className("v-window-header");

    public DialogWindowImpl(By by) {
        super(by);
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .when(hasType(Caption.class)).get(c -> {
                    SelenideElement headerImpl = $(byChain(by, HEADER));
                    return headerImpl.has(exactText(c.getCaption()));
                })
                .when(hasType(CaptionContains.class)).get(cc -> {
                    SelenideElement headerImpl = $(byChain(by, HEADER));
                    return headerImpl.has(text(cc.getCaptionSubstring()));
                })
                .getMatch();
    }

    @Override
    public String getCaption() {
        return $(byChain(by, HEADER))
                .shouldBe(visible)
                .text();
    }

    @Override
    public void close() {
        $(byChain(by, CLOSE_BUTTON))
                .shouldBe(visible)
                .click();
    }
}