package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.MaskedField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;

public class MaskedFieldImpl extends AbstractComponent<MaskedField> implements MaskedField {
    public MaskedFieldImpl(By by) {
        super(by);
    }

    @Override
    public MaskedField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .click();

        impl.sendKeys(Keys.HOME, value);

        return this;
    }

    @Override
    public String getValue() {
        return impl
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }
}