package com.haulmont.masquerade.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public interface Table extends Component<Table> {

    public SelenideElement find(String cellValue);

    public SelenideElement getRow(int rowNumber);

    public ElementsCollection getCells(int row);
}