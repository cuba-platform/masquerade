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
import com.codeborne.selenide.WebDriverRunner;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.Selectors.*;
import com.haulmont.masquerade.components.Table;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.haulmont.masquerade.sys.TagNames;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Quotes;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.Selectors.*;
import static com.haulmont.masquerade.sys.VaadinClassNames.selectedClass;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;

public class TableImpl extends AbstractComponent<Table> implements Table {

    public TableImpl(By by) {
        super(by);
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .when(eq(Conditions.LOADED)).get(() -> {
                    // we have to wait for minimal lazy-loading time
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        return false;
                    }

                    return !$(byClassName("v-loading-indicator")).is(visible);
                })
                .getMatch();
    }

    @Override
    public SelenideElement getRow(By rowBy) {
        return match(rowBy)
                .when(hasType(ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String trsXpath = ".//tr[.//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]]";

                    return $(byChain(by, byClassName("v-table-table"), byXpath(trsXpath)));
                })
                .when(hasType(WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdsXpath = ".//tr[.//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]]";

                    return $(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(ByRowIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(ByIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(BySelected.class)).get(isSelected -> {
                    String tdsXpath = ".//tr[contains(@class, 'v-selected')]";

                    return $(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(ByCells.class)).get(byCells -> {
                    String[] values = byCells.getCellValues();

                    String tds = Arrays.stream(values)
                            .map(text ->
                                    ".//td[contains(@class, 'v-table-cell-content') " +
                                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]")
                            .collect(Collectors.joining(" and "));

                    String trsXpath = ".//tr[" + tds + "]";

                    return $(byChain(by, byClassName("v-table-table"), byXpath(trsXpath)));
                })
                .getMatch();
    }

    @Override
    public ElementsCollection getRows(By rowBy) {
        return match(rowBy)
                .when(hasType(ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String trsXpath = ".//tr[.//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]]";

                    return $$(byChain(by, byClassName("v-table-table"), byXpath(trsXpath)));
                })
                .when(hasType(WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdsXpath = ".//tr[.//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]]";

                    return $$(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(ByRowIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $$(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(ByIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $$(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(BySelected.class)).get(isSelected -> {
                    String tdsXpath = ".//tr[contains(@class, 'v-selected')]";

                    return $$(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .when(hasType(ByCells.class)).get(byCells -> {
                    String[] values = byCells.getCellValues();

                    String tds = Arrays.stream(values)
                            .map(text ->
                                    ".//td[contains(@class, 'v-table-cell-content') " +
                                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]")
                            .collect(Collectors.joining(" and "));

                    String trsXpath = ".//tr[" + tds + "]";

                    return $$(byChain(by, byClassName("v-table-table"), byXpath(trsXpath)));
                })
                .getMatch();
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

                    String tdsXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]";

                    return $$(byChain(by, byXpath(tdsXpath)));
                })
                .when(hasType(ByTargetClassName.class)).get(byClassName -> {
                    String className = byClassName.getExpectedClassName();

                    String tdsXpath = ".//td[contains(@class, 'v-table-cell-content') " +
                            "and contains(@class, " + Quotes.escape(className) + "]";

                    return $$(byChain(by, byXpath(tdsXpath)));
                })
                .when(hasType(ByRowIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]//td[contains(@class, 'v-table-cell-content')]";

                    return $$(byChain(by, byClassName("v-table-table"), byXpath(tdsXpath)));
                })
                .getMatch();
    }

    @Override
    public SelenideElement selectRow(By rowBy) {
        this.shouldBe(VISIBLE)
                .shouldBe(ENABLED);

        SelenideElement row = getRow(rowBy)
                .shouldBe(visible)
                .shouldNotHave(selectedClass);

        row.click();

        return row;
    }

    @Override
    public SelenideElement deselectRow(By rowBy) {
        this.shouldBe(VISIBLE)
                .shouldBe(ENABLED);

        SelenideElement row = getRow(rowBy)
                .shouldBe(visible)
                .shouldHave(cssClass("v-selected"));

        WebDriver webDriver = WebDriverRunner.getWebDriver();
        Actions action = new Actions(webDriver);

        action.keyDown(Keys.CONTROL)
                .click(row.getWrappedElement())
                .keyUp(Keys.CONTROL)
                .build()
                .perform();

        return row;
    }

    @Override
    public ElementsCollection selectRows(By rowBy) {
        this.shouldBe(VISIBLE)
                .shouldBe(LOADED)
                .shouldBe(ENABLED);

        ElementsCollection rows = getRows(rowBy);

        WebDriver webDriver = WebDriverRunner.getWebDriver();
        Actions action = new Actions(webDriver);

        for (SelenideElement row : rows) {
            row.shouldNotHave(selectedClass);

            action.keyDown(Keys.CONTROL)
                    .click(row.getWrappedElement())
                    .keyUp(Keys.CONTROL)
                    .build()
                    .perform();
        }

        return rows;
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
        this.shouldBe(VISIBLE)
                .shouldBe(LOADED);

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