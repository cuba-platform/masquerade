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

package com.haulmont.masquerade.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;
import org.openqa.selenium.By;

public interface Table extends Component<Table> {

    Row getRow(By by);

    Table sort(String columnId, SortDirection direction);

    interface Row extends SelenideElementWrapper<Row>, ByLocator, Element {
        void select();

        SelenideElement getCell(By by);
    }

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

    // Raw API

    SelenideElement find(String cellValue);

    SelenideElement getRow(int rowNumber);

    ElementsCollection getCells(int row);

    ElementsCollection getAllLines();
}