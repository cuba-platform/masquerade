package com.haulmont.masquerade.base;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Wire;
import org.openqa.selenium.By;

public abstract class Composite<T extends Composite> implements SelenideElementWrapper<T>, ByLocator {
    @Wire
    private SelenideElement impl;

    @Wire
    private By by;

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public By getBy() {
        return by;
    }
}