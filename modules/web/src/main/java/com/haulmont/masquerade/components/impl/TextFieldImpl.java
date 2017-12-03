package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.TextField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Conditions.enabled;

public class TextFieldImpl extends AbstractComponent<TextField> implements TextField {

    public TextFieldImpl(By by) {
        super(by);
    }

    @Override
    public TextField setValue(String value) {
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