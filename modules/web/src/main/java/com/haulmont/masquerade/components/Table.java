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
import com.haulmont.masquerade.Selectors;
import com.haulmont.masquerade.util.Log;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Selectors.*;

public interface Table extends Component<Table> {
    SelenideElement getRow(By cellBy);

    ElementsCollection getRows(By cellBy);

    /**
     * Obtain reference to Table cell.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byClassName(String)}</li>
     * </ul>
     *
     * @param cellBy cell selector
     * @return selenide element
     */
    SelenideElement getCell(By cellBy);

    ElementsCollection getCells(By cellBy);

    @Log
    ElementsCollection selectRows(By cellBy);

    @Deprecated
    default SelenideElement find(String cellValue) {
        return getCell(byText(cellValue))
                .shouldBe(visible);
    }

    @Deprecated
    default SelenideElement getRow(int rowNumber) {
        return getRow(byIndex(rowNumber));
    }

    @Deprecated
    default ElementsCollection getCells(int row) {
        return getCells(byRowIndex(row));
    }

    ElementsCollection getAllLines();

    @Log
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