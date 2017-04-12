package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Masquerade;
import com.haulmont.masquerade.Selectors;
import org.openqa.selenium.By;

public interface TextField extends Field {
    void setValue(String value);
    String getValue();

    static TextField of(By by) {
        return Masquerade.mask(by).as(TextField.class);
    }

    static TextField of(String cubaId) {
        return Masquerade.mask(Selectors.byCubaId(cubaId)).as(TextField.class);
    }
}