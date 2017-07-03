package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Table;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

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
        return getAllLines().get(row).findAll("td");
    }

    @Override
    public ElementsCollection getAllLines() {
        // todo wait for loading rows
        return impl.findAll("tr");
    }
}