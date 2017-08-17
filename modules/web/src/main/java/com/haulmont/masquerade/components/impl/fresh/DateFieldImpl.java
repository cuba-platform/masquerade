package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.DateField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

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
    public void setDateValue(String value) {
        $(byChain(by, INPUT))
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .setValue(value)
                .pressEnter();
    }

}