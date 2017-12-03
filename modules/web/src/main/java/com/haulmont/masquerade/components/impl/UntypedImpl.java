package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Untyped;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UntypedImpl implements Untyped {
    private final SelenideElement impl;
    private final By by;

    public UntypedImpl(By by) {
        this.impl = $(by);
        this.by = by;
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public By getBy() {
        return by;
    }
}