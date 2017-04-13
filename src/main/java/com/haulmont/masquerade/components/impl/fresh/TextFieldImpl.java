package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.TextField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TextFieldImpl implements TextField {
    private final SelenideElement impl;

    public TextFieldImpl(By by) {
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
        // todo implement
        return false;
    }

    @Override
    public boolean isEditable() {
        // todo implement
        return false;
    }
}