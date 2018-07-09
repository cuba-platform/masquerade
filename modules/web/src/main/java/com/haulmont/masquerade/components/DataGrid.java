package com.haulmont.masquerade.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.Selectors;
import com.haulmont.masquerade.util.Log;
import org.openqa.selenium.By;

/**
 * DataGrid component.
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
public interface DataGrid extends Component<DataGrid> {
    /**
     * Obtain reference to DataGrid rows.
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
     * Obtain reference to DataGrid rows.
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
     * Obtain reference to DataGrid cell.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byClassName(String)}</li>
     * <li>{@link Selectors#byRowIndex(int)}</li>
     * <li>{@link Selectors#byRowColIndexes(int, int)}</li>
     * </ul>
     *
     * @param cellBy cell selector
     * @return selenide element
     */
    SelenideElement getCell(By cellBy);

    /**
     * Obtain reference to DataGrid cell.
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
     * Select DataGrid row.
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
    SelenideElement selectRow(By rowBy);

    /**
     * Deselect DataGrid row.
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
    SelenideElement deselectRow(By rowBy);

    /**
     * Select DataGrid rows.
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
    ElementsCollection selectRows(By rowBy);

    /**
     * Select DataGrid header cell.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byClassName(String)}</li>
     * <li>{@link Selectors#byCubaId(String)}</li>
     * </ul>
     *
     * @param cellBy cell selector
     * @return selenide element
     */
    SelenideElement getHeaderCell(By cellBy);

    /**
     * Select DataGrid header cell.
     * <br>
     * @param columnId cell selector
     * @return selenide element
     */
    SelenideElement getHeaderCell(String columnId);

    SelenideElement getDetailsRow();

    @Log
    DataGrid sort(String columnId, DataGrid.SortDirection direction);

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
