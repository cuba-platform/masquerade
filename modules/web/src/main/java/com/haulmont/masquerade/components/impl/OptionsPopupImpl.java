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
import com.haulmont.masquerade.components.Component;
import com.haulmont.masquerade.conditions.Options;
import com.haulmont.masquerade.conditions.OptionsCount;
import com.haulmont.masquerade.conditions.SpecificCondition;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.components.HasOptionsPopup.OptionsPopup;
import static com.haulmont.masquerade.components.impl.LookupFieldImpl.*;
import static com.haulmont.masquerade.sys.TagNames.SPAN;
import static com.haulmont.masquerade.sys.TagNames.TD;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.className;

public class OptionsPopupImpl<T extends Component>
        extends AbstractSpecificConditionHandler<OptionsPopup>
        implements OptionsPopup<T> {

    private final By by;
    private final SelenideElement impl;
    private T parent;

    public OptionsPopupImpl(By by, T parent) {
        this.by = by;
        this.impl = $(by);
        this.parent = parent;
    }

    @Override
    public List<String> getVisibleOptions() {
        return $$(byChain(by, TD, SPAN)).texts();
    }

    @Override
    public T select(String option) {
        if (isNullOrEmpty(option)) {
            $(byChain(by, TD, EMPTY_OPTION))
                    .shouldBe(visible)
                    .click();
        } else {
            $(byChain(by, TD, byText(option)))
                    .shouldBe(visible)
                    .click();
        }

        return parent;
    }

    @Override
    public T select(By by) {
        $(byChain(by, TD, by))
                .shouldBe(visible)
                .click();
        return parent;
    }

    @Override
    public OptionsPopup<T> nextPage() {
        $(byChain(by, className(V_FILTERSELECT_NEXTPAGE)))
                .shouldBe(visible)
                .click();

        this.shouldBe(visible);

        return this;
    }

    @Override
    public boolean hasNextPage() {
        return $(byChain(by, className(V_FILTERSELECT_NEXTPAGE))).isDisplayed();
    }

    @Override
    public OptionsPopup<T> previousPage() {
        $(byChain(by, className(V_FILTERSELECT_PREVPAGE)))
                .shouldBe(visible)
                .click();

        this.shouldBe(visible);

        return this;
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public By getBy() {
        return by;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public boolean apply(SpecificCondition condition) {
        return match(condition)
                .when(hasType(Options.class)).get(opts -> {
                    List<String> options = opts.getOptions().stream()
                            .map(o -> isNullOrEmpty(o) ? EMPTY_OPTION_VALUE : o)
                            .collect(Collectors.toList());

                    List<String> texts = $$(byChain(by, TD, SPAN)).texts();
                    return texts.equals(options);
                })
                .when(hasType(OptionsCount.class)).get(optsCount -> {
                    return $$(byChain(by, TD, SPAN)).size() == optsCount.getCount();
                })
                .getMatch();
    }

    @Override
    public Component getParent() {
        return parent;
    }
}