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
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.Selectors;
import com.haulmont.masquerade.util.Log;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Selectors.*;

/**
 * Table component.
 * <br>
 * Supported conditions:
 * <ul>
 * <li>{@link Conditions#VISIBLE}</li>
 * <li>{@link Conditions#HIDDEN}</li>
 * <li>{@link Conditions#ENABLED}</li>
 * <li>{@link Conditions#DISABLED}</li>
 * <li>{@link Conditions#LOADED}</li>
 * </ul>
 */
public interface Table extends Component<Table> {
    /**
     * Obtain reference to Table rows.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#byCells(String...)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * <li>{@link Selectors#byIndex(int)}</li>
     * <li>{@link Selectors#isSelected()}</li>
     * <li>{@link Selectors#isVisible()}</li>
     * </ul>
     *
     * @param rowBy row selector
     * @return selenide element
     */
    SelenideElement getRow(By rowBy);

    /**
     * Obtain reference to Table rows.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#byCells(String...)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * <li>{@link Selectors#byIndex(int)}</li>
     * <li>{@link Selectors#isSelected()}</li>
     * <li>{@link Selectors#isVisible()}</li>
     * </ul>
     *
     * @param rowBy row selector
     * @return selenide element collection
     */
    ElementsCollection getRows(By rowBy);

    /**
     * Obtain reference to Table cell.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byClassName(String)}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * </ul>
     *
     * @param cellBy cell selector
     * @return selenide element
     */
    SelenideElement getCell(By cellBy);

    /**
     * Obtain reference to Table cell.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byClassName(String)}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * </ul>
     *
     * @param cellBy cell selector
     * @return selenide element
     */
    ElementsCollection getCells(By cellBy);

    /**
     * Select Table row.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#byCells(String...)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#isSelected()}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * <li>{@link Selectors#byIndex(int)}</li>
     * </ul>
     *
     * @param rowBy row selector
     * @return selenide element collection
     */
    @Log
    SelenideElement selectRow(By rowBy);

    /**
     * Deselect Table row.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#byCells(String...)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#isSelected()}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * <li>{@link Selectors#byIndex(int)}</li>
     * </ul>
     *
     * @param rowBy row selector
     * @return selenide element collection
     */
    @Log
    SelenideElement deselectRow(By rowBy);

    /**
     * Select Table rows.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#byCells(String...)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#isSelected()}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * <li>{@link Selectors#byIndex(int)}</li>
     * </ul>
     *
     * @param rowBy row selector
     * @return selenide element collection
     */
    @Log
    ElementsCollection selectRows(By rowBy);

    /**
     * Will be removed in 2.0
     *
     * @deprecated Use {@link #getCell(By)} with {@link Selectors#byText(String)}
     */
    @Deprecated
    default SelenideElement find(String cellValue) {
        return getCell(byText(cellValue))
                .shouldBe(visible);
    }

    /**
     * Will be removed in 2.0
     *
     * @deprecated Use {@link #getRow(By)} with {@link Selectors#byIndex(int)}
     */
    @Deprecated
    default SelenideElement getRow(int rowNumber) {
        return getRow(byIndex(rowNumber));
    }

    /**
     * Will be removed in 2.0
     *
     * @deprecated Use {@link #getCells(By)} with {@link Selectors#byRowIndex(int)}
     */
    @Deprecated
    default ElementsCollection getCells(int row) {
        return getCells(byRowIndex(row));
    }

    /**
     * Note that: method will wait for data loading at least for 400 ms.
     *
     * @deprecated Use {@link #getRows(By)} with {@link Selectors#isVisible()}
     * @return all rows
     */
    @Deprecated
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