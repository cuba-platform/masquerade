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
import com.haulmont.masquerade.Selectors;
import com.haulmont.masquerade.components.Tree;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Quotes;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.Motif.match;

public class TreeImpl extends AbstractComponent<Tree> implements Tree {
    public TreeImpl(By by) {
        super(by);
    }

    @Override
    public SelenideElement getNode(By nodeBy) {
        return match(nodeBy)
                .when(hasType(Selectors.ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-tree8-cell') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(Selectors.WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-tree8-cell') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(Selectors.ByTargetClassName.class)).get(byClassName -> {
                    String className = byClassName.getExpectedClassName();

                    String tdXpath = ".//td[contains(@class, 'v-tree8-cell') " +
                            "and contains(@class, " + Quotes.escape(className) + "]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(Selectors.ByRowIndex.class)).get(byColRow -> {
                    int rowIndex = byColRow.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + rowIndex + "]//td[" + 1 +"]";

                    return $(byChain(by, byClassName("v-tree8-body"), byXpath(tdsXpath)));
                })
                .getMatch();
    }
}