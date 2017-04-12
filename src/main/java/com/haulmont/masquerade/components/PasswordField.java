package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Masquerade;
import com.haulmont.masquerade.Selectors;
import org.openqa.selenium.By;

public interface PasswordField extends TextField {
    static PasswordField of(By by) {
        return Masquerade.mask(by).as(PasswordField.class);
    }

    static PasswordField of(String cubaId) {
        return Masquerade.mask(Selectors.byCubaId(cubaId)).as(PasswordField.class);
    }
}