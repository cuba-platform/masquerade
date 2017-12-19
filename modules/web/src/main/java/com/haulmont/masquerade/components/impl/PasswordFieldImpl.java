package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.PasswordField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;

public class PasswordFieldImpl extends AbstractInputComponent<PasswordField> implements PasswordField {

    public PasswordFieldImpl(By by) {
        super(by);
    }

    @Override
    protected SelenideElement getInputDelegate() {
        return impl;
    }

    @Override
    public PasswordField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .setValue(value);
        return this;
    }

    @Override
    public String getValue() {
        return impl.shouldBe(visible).getValue();
    }
}