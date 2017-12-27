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

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Selectors.ByRowIndex;
import com.haulmont.masquerade.Selectors.ByTargetClassName;
import com.haulmont.masquerade.Selectors.ByTargetText;
import com.haulmont.masquerade.Selectors.WithTargetText;
import com.haulmont.masquerade.components.Table;
import com.haulmont.masquerade.sys.TagNames;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Quotes;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.Motif.match;

public class TableImpl extends AbstractComponent<Table> implements Table {

    public static final String V_SELECTED = "v-selected";

    public TableImpl(By by) {
        super(by);
    }

    @Override
    public ElementsCollection getRows(By cellBy) {
        // todo
        return null;
    }

    @Override
    public ElementsCollection getCells(By cellBy) {
        return match(cellBy)
                .when(hasType(ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]";

                    return $$(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]";

                    return $$(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(ByTargetClassName.class)).get(byClassName -> {
                    String className = byClassName.getExpectedClassName();

                    String tdXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and contains(@class, " + Quotes.escape(className) + "]";

                    return $$(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(ByRowIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex();

                    // todo
                    String tdXpath = "";

                    return $$(byChain(by, byXpath(tdXpath)));
                })
                .getMatch();
    }

    @Override
    public SelenideElement getRow(By cellBy) {
        // todo
        return null;
    }

    @Override
    public SelenideElement getCell(By cellBy) {
        return match(cellBy)
                .when(hasType(ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(ByTargetClassName.class)).get(byClassName -> {
                    String className = byClassName.getExpectedClassName();

                    String tdXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and contains(@class, " + Quotes.escape(className) + "]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .getMatch();
    }

    @Override
    public ElementsCollection selectRows(By cellBy) {
        // todo
        return null;
    }

    @Override
    public SelenideElement find(String cellValue) {
        SelenideElement textElement = $(byChain(by, byClassName("v-table-cell-content"), byText(cellValue)))
                .shouldBe(visible);

        return textElement.parent().parent();
    }

    @Override
    public SelenideElement getRow(int rowNumber) {
        return getAllLines().get(rowNumber);
    }

    @Override
    public ElementsCollection getCells(int row) {
        return getAllLines().get(row).findAll(TagNames.TD);
    }

    @Override
    public ElementsCollection getAllLines() {
        // todo wait for loading rows
        return impl.findAll(TagNames.TR);
    }

    @Override
    public Table sort(String columnId, SortDirection direction) {
        if (columnId.startsWith("column_")) {
            columnId = columnId.substring("column_".length());
        }

        SelenideElement columnHeaderCell = $(byChain(by, byClassName("v-table-header"), byCubaId("column_" + columnId)))
                .shouldBe(visible)
                .shouldHave(cssClass("v-table-header-sortable"));

        SortDirection currentDirection = getSortDirection(columnHeaderCell);

        // lets calculate exact click count, because sort can be slow
        for (int i = 0; i < getSortClickCount(currentDirection, direction); i++) {
            columnHeaderCell.click();
        }

        // final check
        String directionSuffix = toSortSuffix(direction);
        if (!directionSuffix.isEmpty()) {
            columnHeaderCell.shouldHave(cssClass("v-table-header-cell-" + directionSuffix));
        } else {
            columnHeaderCell
                    .shouldNotHave(cssClass("v-table-header-cell-asc"))
                    .shouldNotHave(cssClass("v-table-header-cell-desc"));
        }

        return this;
    }

    protected SortDirection getSortDirection(SelenideElement columnHeaderCell) {
        if (columnHeaderCell.has(cssClass("v-table-header-cell-asc"))) {
            return SortDirection.ASCENDING;
        }
        if (columnHeaderCell.has(cssClass("v-table-header-cell-desc"))) {
            return SortDirection.DESCENDING;
        }
        return SortDirection.NONE;
    }

    protected String toSortSuffix(SortDirection direction) {
        switch (direction) {
            case ASCENDING:
                return "asc";
            case DESCENDING:
                return "desc";
            case NONE:
                return "";
            default:
                throw new IllegalArgumentException("Unsupported direction");
        }
    }

    protected int getSortClickCount(SortDirection current, SortDirection target) {
        if (current == target) {
            return 0;
        }

        if ((current == SortDirection.ASCENDING && target == SortDirection.DESCENDING)
                || (current == SortDirection.NONE && target == SortDirection.ASCENDING)
                || (current == SortDirection.DESCENDING && target == SortDirection.NONE)) {
            return 1;
        }

        return 2;
    }
}