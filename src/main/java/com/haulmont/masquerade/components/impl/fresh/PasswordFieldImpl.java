package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.PasswordField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PasswordFieldImpl implements PasswordField {

    private final SelenideElement impl;

    public PasswordFieldImpl(By by) {
        this.impl = $(by);
    }

    @Override
    public void setValue(String value) {
        impl.shouldBe(visible).setValue(value);
    }

    @Override
    public String getValue() {
        return impl.shouldBe(visible).getValue();
    }

    @Override
    public boolean isEnabled() {
        // todo implement
        return false;
    }

    @Override
    public boolean isVisible() {
        return is(Condition.visible);
    }

    @Override
    public boolean isEditable() {
        // todo implement
        return false;
    }

    @Override
    public SelenideElement delegate() {
        return impl;
    }
}