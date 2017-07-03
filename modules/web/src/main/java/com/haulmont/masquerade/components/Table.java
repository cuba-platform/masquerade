package com.haulmont.masquerade.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public interface Table extends Component<Table> {

    SelenideElement find(String cellValue);

    SelenideElement getRow(int rowNumber);

    ElementsCollection getCells(int row);

    ElementsCollection getAllLines();
}