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
import com.haulmont.masquerade.Selectors.ByCubaId;
import com.haulmont.masquerade.Selectors.ByIndex;
import com.haulmont.masquerade.Selectors.ByTargetText;
import com.haulmont.masquerade.Selectors.WithTargetText;
import com.haulmont.masquerade.components.Component;
import com.haulmont.masquerade.components.TabSheet;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.className;

public class TabSheetImpl extends AbstractComponent<TabSheet> implements TabSheet {
    public TabSheetImpl(By by) {
        super(by);
    }

    @Override
    public Tab getTab(By tabBy) {
        // todo
        return match(tabBy)
                .when(hasType(ByTargetText.class)).get(byText -> {
                    return (Tab)null;
                })
                .when(hasType(WithTargetText.class)).get(withText -> {
                    return (Tab)null;
                })
                .when(hasType(ByIndex.class)).get(byIndex -> {
                    return (Tab)null;
                })
                .when(hasType(ByCubaId.class)).get(byCubaId -> {
                    return (Tab)null;
                })
                .getMatch();
    }

    @Override
    public List<Tab> getVisibleTabs() {
        return null;
    }

    public class TabImpl implements Tab {
        protected final SelenideElement impl;
        protected final By by;

        public TabImpl(By by) {
            this.by = by;
            this.impl = $(by);
        }

        @Override
        public void select() {
            impl.shouldBe(visible)
                    .shouldNotHave(cssClass("v-tabsheet-tabitemcell-selected"))
                    .find(className("v-caption"))
                    .click();
        }

        @Override
        public void close() {
            // todo
        }

        @Override
        public By getBy() {
            return by;
        }

        @Override
        public SelenideElement getDelegate() {
            return impl;
        }

        @Override
        public Component getParent() {
            return TabSheetImpl.this;
        }
    }
}