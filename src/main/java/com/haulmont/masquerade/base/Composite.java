package com.haulmont.masquerade.base;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Connect;

public abstract class Composite<T extends Composite> implements SelenideElementWrapper<T> {
    @Connect
    private SelenideElement impl;

    @Override
    public SelenideElement delegate() {
        return impl;
    }
}