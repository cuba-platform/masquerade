package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.TextField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;

public class TextFieldImpl extends AbstractInputComponent<TextField> implements TextField {

    public TextFieldImpl(By by) {
        super(by);
    }

    @Override
    protected SelenideElement getInputDelegate() {
        return impl;
    }

    @Override
    public TextField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .setValue(value);
        return this;
    }

    @Override
    public String getValue() {
        return impl.shouldBe(visible)
                .getValue();
    }
}