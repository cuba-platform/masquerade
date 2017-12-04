package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.DateTimeField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static org.openqa.selenium.By.cssSelector;

public class DateTimeFieldImpl extends AbstractComponent<DateTimeField> implements DateTimeField {

    public static final By DATEPART = cssSelector("div[class*='popupcalendar'] > input");
    public static final By TIMEPART = cssSelector("input[class*='maskedfield']");

    public DateTimeFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getDateValue() {
        return $(byChain(by, DATEPART))
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

    @Override
    public DateTimeField setDateValue(String value) {
        SelenideElement dateFieldImpl = $(byChain(by, DATEPART));
        dateFieldImpl
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .click();

        dateFieldImpl.sendKeys(Keys.HOME, value);
        return this;
    }

    @Override
    public String getTimeValue() {
        return $(byChain(by, TIMEPART))
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

    @Override
    public DateTimeField setTimeValue(String value) {
        SelenideElement timeFieldImpl = $(byChain(by, TIMEPART));

        timeFieldImpl
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .click();

        timeFieldImpl.sendKeys(Keys.HOME, value);
        return this;
    }
}