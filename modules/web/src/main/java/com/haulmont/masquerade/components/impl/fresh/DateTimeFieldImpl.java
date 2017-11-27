package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.DateTimeField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.TagNames.INPUT;

public class DateTimeFieldImpl extends AbstractComponent<DateTimeField> implements DateTimeField {

    public static final By DATEPART = byCubaId("datepart");
    public static final By TIMEPART = byCubaId("timepart");

    public DateTimeFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getDateValue() {
        return $(byChain(by, DATEPART, INPUT))
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