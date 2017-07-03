package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.TextArea;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;

public class TextAreaImpl extends AbstractComponent<TextArea> implements TextArea {

    public TextAreaImpl(By by) {
        super(by);
    }

    @Override
    public TextArea setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .setValue(value);
        return this;
    }

    @Override
    public String getValue() {
        return impl.shouldBe(visible).getValue();
    }
}