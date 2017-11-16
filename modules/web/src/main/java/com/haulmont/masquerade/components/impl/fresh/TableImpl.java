package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Table;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import com.haulmont.masquerade.sys.TagNames;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;

public class TableImpl extends AbstractComponent<Table> implements Table {
    public TableImpl(By by) {
        super(by);
    }

    @Override
    public SelenideElement find(String cellValue) {
        return getAllLines().find(Condition.text(cellValue));
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