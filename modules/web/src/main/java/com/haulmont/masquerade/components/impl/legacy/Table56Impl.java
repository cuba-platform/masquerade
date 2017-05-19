package com.haulmont.masquerade.components.impl.legacy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Table;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Table56Impl implements Table {
    private final SelenideElement impl;
    private final By by;

    public Table56Impl(By by) {
        this.impl = $(by);
        this.by = by;
    }

    public SelenideElement find(String cellValue) {
        return getAllLines().find(Condition.text(cellValue));
    }

    public SelenideElement getRow(int rowNumber) {
        return getAllLines().get(rowNumber);
    }

    public ElementsCollection getCells(int row) {
        return getAllLines().get(row).findAll("td");
    }

    public ElementsCollection getAllLines() {
        // todo wait for loading rows
        return impl.findAll("tr");
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }
}