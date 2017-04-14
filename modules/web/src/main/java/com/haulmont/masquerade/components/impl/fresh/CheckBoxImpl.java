package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.components.CheckBox;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckBoxImpl implements CheckBox {
    private By by;
    private SelenideElement impl;

    public CheckBoxImpl(By by) {
        this.by = by;
        this.impl = $(by);
    }

    @Override
    public void set() {
        // todo implement
    }

    @Override
    public void unset() {
        // todo implement
    }

    @Override
    public void isSet() {
        // todo implement
    }

    @Override
    public String getCaption() {
        // todo implement
        return null;
    }

    @Override
    public boolean isEnabled() {
        return is(Conditions.enabled);
    }

    @Override
    public SelenideElement delegate() {
        return impl;
    }

    @Override
    public boolean isEditable() {
        return is(Conditions.editable);
    }
}