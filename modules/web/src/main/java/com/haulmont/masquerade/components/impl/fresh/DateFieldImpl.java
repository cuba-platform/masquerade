package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.DateField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;

public class DateFieldImpl extends AbstractComponent<DateField> implements DateField {

    public DateFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getDateValue() {
        return $(byChain(by, INPUT))
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

    @Override
    public DateField setDateValue(String value) {
        SelenideElement inputImpl = $(byChain(by, INPUT));

        inputImpl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .click();

        inputImpl.sendKeys(Keys.HOME, value);

        return this;
    }
}