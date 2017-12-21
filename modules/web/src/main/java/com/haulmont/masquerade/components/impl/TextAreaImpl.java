package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.TextArea;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.TEXTAREA;

public class TextAreaImpl extends AbstractInputComponent<TextArea> implements TextArea {

    public TextAreaImpl(By by) {
        super(by);
    }

    @Override
    public TextArea setValue(String value) {
        getInputDelegate()
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .setValue(value);
        return this;
    }

    @Override
    public String getValue() {
        return getInputDelegate()
                .shouldBe(visible)
                .getValue();
    }

    @Override
    protected SelenideElement getInputDelegate() {
        return impl;
    }
}