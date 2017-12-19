package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.MaskedField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;

public class MaskedFieldImpl extends AbstractInputComponent<MaskedField> implements MaskedField {
    public MaskedFieldImpl(By by) {
        super(by);
    }

    @Override
    public MaskedField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .click();

        impl.sendKeys(Keys.HOME, value);

        return this;
    }

    @Override
    public String getValue() {
        return impl
                .shouldBe(visible)
                .getValue();
    }
}