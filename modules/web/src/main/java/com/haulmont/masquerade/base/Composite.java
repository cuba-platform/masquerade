package com.haulmont.masquerade.base;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Wire;

public abstract class Composite<T extends Composite> implements SelenideElementWrapper<T> {
    @Wire
    private SelenideElement impl;

    @Override
    public SelenideElement delegate() {
        return impl;
    }
}