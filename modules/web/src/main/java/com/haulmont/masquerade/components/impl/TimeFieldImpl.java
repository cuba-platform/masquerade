package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.TimeField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;

public class TimeFieldImpl extends AbstractInputComponent<TimeField> implements TimeField {
    public TimeFieldImpl(By by) {
        super(by);
    }

    @Override
    protected SelenideElement getInputDelegate() {
        return impl;
    }

    @Override
    public String getValue() {
        return impl
                .shouldBe(visible)
                .getValue();
    }

    @Override
    public TimeField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .click();

        impl.sendKeys(Keys.HOME, value);
        return this;
    }
}