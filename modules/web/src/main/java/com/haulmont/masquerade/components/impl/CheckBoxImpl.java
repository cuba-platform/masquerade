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

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.components.CheckBox;
import com.haulmont.masquerade.conditions.Caption;
import com.haulmont.masquerade.conditions.SpecificCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Objects;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;
import static com.haulmont.masquerade.sys.TagNames.LABEL;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;

public class CheckBoxImpl extends AbstractComponent<CheckBox> implements CheckBox {

    public CheckBoxImpl(By by) {
        super(by);
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .when(eq(Conditions.CHECKED)).get(() ->
                        $(byChain(by, INPUT)).is(Condition.checked)
                )
                .when(eq(Conditions.SELECTED)).get(() ->
                        $(byChain(by, INPUT)).is(Condition.selected)
                )
                .when(hasType(Caption.class)).get(c ->
                        Objects.equals(getCaption(), c.getCaption())
                )
                .getMatch();
    }

    @Override
    public CheckBox setChecked(boolean checked) {
        SelenideElement checkBoxInput = $(byChain(by, INPUT))
                .shouldBe(visible)
                .shouldBe(enabled);

        if (checked != checkBoxInput.is(Condition.checked)) {
            checkBoxInput.sendKeys(Keys.SPACE);
        }

        return this;
    }

    @Override
    public boolean isChecked() {
        return is(Conditions.CHECKED);
    }

    @Override
    public String getCaption() {
        return $(byChain(by, LABEL)).getText();
    }
}