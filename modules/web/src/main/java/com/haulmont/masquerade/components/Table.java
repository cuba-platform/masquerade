package com.haulmont.masquerade.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public interface Table extends Component<Table> {

    SelenideElement find(String cellValue);

    SelenideElement getRow(int rowNumber);

    ElementsCollection getCells(int row);

    ElementsCollection getAllLines();

    Table sort(String columnId, SortDirection direction);

    enum SortDirection {
        /**
         * Ascending (e.g. A-Z, 1..9) sort order
         */
        ASCENDING,

        /**
         * Descending (e.g. Z-A, 9..1) sort order
         */
        DESCENDING,

        /**
         * None
         */
        NONE
    }
}