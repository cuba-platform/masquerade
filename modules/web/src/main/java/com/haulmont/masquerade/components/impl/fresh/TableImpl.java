package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Table;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

import static java.lang.String.format;

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

//    the order can be asc or desc
    @Override
    public Table sort(String columnId, String order) throws Exception{
        SelenideElement cell = this.getDelegate().$(By.xpath(format("//td[@cuba-id='%s']", columnId)));
        if (cell.getAttribute("class").contains("v-table-header-sortable")) {
            while (!(cell.getAttribute("class").contains("v-table-header-cell-" + order))) {
                cell.click();
            }
        }
        else {
            throw new Exception("The table header is not sortable");
        }
        return this;
    }


}