package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Label;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

// todo Conditions.text()
// todo Conditions.value()
public class LabelImpl implements Label {
    private final By by;
    private final SelenideElement impl;

    public LabelImpl(By by) {
        this.by = by;
        this.impl = $(by);
    }

    @Override
    public String getValue() {
        return impl.getText();
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }
}