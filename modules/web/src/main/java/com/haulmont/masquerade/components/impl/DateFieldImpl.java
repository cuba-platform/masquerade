package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.DateField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;

public class DateFieldImpl extends AbstractInputComponent<DateField> implements DateField {

    public DateFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getDateValue() {
        return getInputDelegate()
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

    @Override
    public DateField setDateValue(String value) {
        getInputDelegate().shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .click();

        getInputDelegate().sendKeys(Keys.HOME, value);

        return this;
    }
}