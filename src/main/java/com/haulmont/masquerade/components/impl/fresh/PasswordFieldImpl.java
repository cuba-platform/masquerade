package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.PasswordField;
import org.openqa.selenium.By;

public class PasswordFieldImpl extends TextFieldImpl implements PasswordField {
    public PasswordFieldImpl(By by) {
        super(by);
    }
}