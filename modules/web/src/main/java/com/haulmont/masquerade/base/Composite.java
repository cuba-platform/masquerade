package com.haulmont.masquerade.base;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.components.Container;
import org.openqa.selenium.By;

/**
 * Convenient parent class for composite UI components: panels, screens, tabs, etc.
 *
 * @param <T> type of class
 */
public abstract class Composite<T> implements Container<T> {
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