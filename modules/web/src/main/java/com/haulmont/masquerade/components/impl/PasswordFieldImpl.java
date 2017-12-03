package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.PasswordField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Conditions.enabled;

public class PasswordFieldImpl extends AbstractComponent<PasswordField> implements PasswordField {

    public PasswordFieldImpl(By by) {
        super(by);
    }

    @Override
    public PasswordField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .setValue(value);
        return this;
    }

    @Override
    public String getValue() {
        return impl.shouldBe(visible).getValue();
    }
}