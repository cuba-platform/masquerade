package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.Selectors;
import com.haulmont.masquerade.components.DataGrid;
import com.haulmont.masquerade.conditions.SpecificCondition;
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
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.haulmont.masquerade.Conditions.ENABLED;
import static com.haulmont.masquerade.Conditions.LOADED;
import static com.haulmont.masquerade.Conditions.VISIBLE;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.VaadinClassNames.selectedClass;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;

public class DataGridImpl extends AbstractComponent<DataGrid> implements DataGrid {
    public DataGridImpl(By by) {
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

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public SelenideElement getRow(By rowBy) {
        return match(rowBy)
                .when(hasType(Selectors.ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String trsXpath = ".//tr[.//td[contains(@class, 'v-grid-cell') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]]";

                    return $(byChain(by, byClassName("v-grid-body"), byXpath(trsXpath)));
                })
                .when(hasType(Selectors.WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdsXpath = ".//tr[.//td[contains(@class, 'v-grid-cell') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]]";

                    return $(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByRowIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.BySelected.class)).get(isSelected -> {
                    String tdsXpath = ".//tr[contains(@class, 'v-grid-row-selected')]";

                    return $(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByCells.class)).get(byCells -> {
                    String[] values = byCells.getCellValues();

                    String tds = Arrays.stream(values)
                            .map(text ->
                                    ".//td[contains(@class, 'v-grid-cell') " +
                                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]")
                            .collect(Collectors.joining(" and "));

                    String trsXpath = ".//tr[" + tds + "]";

                    return $(byChain(by, byClassName("v-grid-body"), byXpath(trsXpath)));
                })
                .when(hasType(Selectors.ByVisibleRows.class)).get(byVisibleRows -> {
                    return $(byChain(by, byClassName("v-grid-body"), byXpath(".//tr")));
                })
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public ElementsCollection getRows(By rowBy) {
        return match(rowBy)
                .when(hasType(Selectors.ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String trsXpath = ".//tr[.//td[contains(@class, 'v-grid-cell') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]]";

                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(trsXpath)));
                })
                .when(hasType(Selectors.WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdsXpath = ".//tr[.//td[contains(@class, 'v-grid-cell') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]]";

                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByRowIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]";

                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.BySelected.class)).get(isSelected -> {
                    String tdsXpath = ".//tr[contains(@class, 'v-grid-row-selected')]";

                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByCells.class)).get(byCells -> {
                    String[] values = byCells.getCellValues();

                    String tds = Arrays.stream(values)
                            .map(text ->
                                    ".//td[contains(@class, 'v-grid-cell') " +
                                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]")
                            .collect(Collectors.joining(" and "));

                    String trsXpath = ".//tr[" + tds + "]";

                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(trsXpath)));
                })
                .when(hasType(Selectors.ByVisibleRows.class)).get(byVisibleRows -> {
                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(".//tr")));
                })
                .getMatch();
    }

    @Override
    public SelenideElement getCell(By cellBy) {
        return match(cellBy)
                .when(hasType(Selectors.ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-grid-cell') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(Selectors.WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdXpath = ".//td[contains(@class, 'v-grid-cell') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(Selectors.ByTargetClassName.class)).get(byClassName -> {
                    String className = byClassName.getExpectedClassName();

                    String tdXpath = ".//td[contains(@class, 'v-grid-cell') " +
                            "and contains(@class, " + Quotes.escape(className) + "]";

                    return $(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(Selectors.ByRowColIndexes.class)).get(byColRow -> {
                    int rowIndex = byColRow.getRowIndex() + 1;
                    int colIndex = byColRow.getColIndex() + 1;

                    String tdsXpath = "(.//tr)[" + rowIndex + "]//td[" + colIndex +"]";

                    return $(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .getMatch();
    }

    @Override
    public ElementsCollection getCells(By cellBy) {
        return match(cellBy)
                .when(hasType(Selectors.ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String tdXpath = ".//t[contains(@class, 'v-grid-cell') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]";

                    return $$(byChain(by, byXpath(tdXpath)));
                })
                .when(hasType(Selectors.WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdsXpath = ".//td[contains(@class, 'v-grid-cell') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]";

                    return $$(byChain(by, byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByTargetClassName.class)).get(byClassName -> {
                    String className = byClassName.getExpectedClassName();

                    String tdsXpath = ".//td[contains(@class, 'v-grid-cell') " +
                            "and contains(@class, " + Quotes.escape(className) + "]";

                    return $$(byChain(by, byXpath(tdsXpath)));
                })
                .when(hasType(Selectors.ByRowIndex.class)).get(byRowIndex -> {
                    int index = byRowIndex.getIndex() + 1;

                    String tdsXpath = "(.//tr)[" + index + "]//td[contains(@class, 'v-grid-cell')]";

                    return $$(byChain(by, byClassName("v-grid-body"), byXpath(tdsXpath)));
                })
                .getMatch();
    }

    @Override
    public SelenideElement selectRow(By rowBy) {
        this.shouldBe(VISIBLE)
                .shouldBe(ENABLED);

        SelenideElement row = getRow(rowBy)
                .shouldBe(visible);

        row.click();

        return row;
    }

    @Override
    public SelenideElement deselectRow(By rowBy) {
        this.shouldBe(VISIBLE)
                .shouldBe(ENABLED);

        SelenideElement row = getRow(rowBy)
                .shouldBe(visible)
                .shouldHave(selectedClass);

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
    public SelenideElement getHeaderCell(By cellBy) {
        return match(cellBy)
                .when(hasType(Selectors.ByTargetText.class)).get(byText -> {
                    String text = byText.getElementText();

                    String tdXpath = ".//th[contains(@class, 'v-grid-cell') " +
                            "and .//text()[normalize-space(.) = " + Quotes.escape(text) + "]]";

                    return $(byChain(by,byClassName("v-grid-header"), byXpath(tdXpath)));
                })
                .when(hasType(Selectors.WithTargetText.class)).get(withText -> {
                    String text = withText.getElementText();

                    String tdXpath = ".//th[contains(@class, 'v-grid-cell') " +
                            "and .//text()[contains(normalize-space(.), " + Quotes.escape(text) + ")]]";

                    return $(byChain(by,byClassName("v-grid-header"), byXpath(tdXpath)));
                })
                .when(hasType(Selectors.ByTargetClassName.class)).get(byClassName -> {
                    String className = byClassName.getExpectedClassName();

                    String tdXpath = ".//th[contains(@class, 'v-grid-cell') " +
                            "and contains(@class, " + Quotes.escape(className) + "]";

                    return $(byChain(by,byClassName("v-grid-header"), byXpath(tdXpath)));
                })
                .when(hasType(Selectors.ByCubaId.class)).get(byCubaId -> {

                    String cubaId = byCubaId.getCubaId();

                    return $(byChain(by, byClassName("v-grid-header"), byCubaId(cubaId)));
                })
                .getMatch();
    }

    @Override
    public SelenideElement getHeaderCell(String columnId) {
        return  $(byChain(by, byClassName("v-grid-header"), byCubaId("column_" + columnId)));
    }

    @Override
    public SelenideElement getDetailsRow() {
        return $(byChain(by, byClassName("v-grid-spacer")));
    }

    @Override
    public DataGrid sort(String columnId, SortDirection direction) {
        if (columnId.startsWith("column_")) {
            columnId = columnId.substring("column_".length());
        }

        SelenideElement columnHeaderCell = $(byChain(by, byClassName("v-grid-header"), byCubaId("column_" + columnId)))
                .shouldBe(visible)
                .shouldHave(cssClass("sortable"));

        DataGrid.SortDirection currentDirection = getSortDirection(columnHeaderCell);

        // lets calculate exact click count, because sort can be slow
        for (int i = 0; i < getSortClickCount(currentDirection, direction); i++) {
            columnHeaderCell.click();
        }

        // final check
        String directionSuffix = toSortSuffix(direction);
        if (!directionSuffix.isEmpty()) {
            columnHeaderCell.shouldHave(cssClass("sort-" + directionSuffix));
        } else {
            columnHeaderCell
                    .shouldNotHave(cssClass("sort-asc"))
                    .shouldNotHave(cssClass("sort-desc"));
        }

        return this;
    }

    protected DataGrid.SortDirection getSortDirection(SelenideElement columnHeaderCell) {
        if (columnHeaderCell.has(cssClass("sort-asc"))) {
            return DataGrid.SortDirection.ASCENDING;
        }
        if (columnHeaderCell.has(cssClass("sort-desc"))) {
            return DataGrid.SortDirection.DESCENDING;
        }
        return DataGrid.SortDirection.NONE;
    }

    protected String toSortSuffix(DataGrid.SortDirection direction) {
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

    protected int getSortClickCount(DataGrid.SortDirection current, DataGrid.SortDirection target) {
        if (current == target) {
            return 0;
        }

        if ((current == DataGrid.SortDirection.ASCENDING && target == DataGrid.SortDirection.DESCENDING)
                || (current == DataGrid.SortDirection.NONE && target == DataGrid.SortDirection.ASCENDING)
                || (current == DataGrid.SortDirection.DESCENDING && target == DataGrid.SortDirection.NONE)) {
            return 1;
        }

        return 2;
    }
}
