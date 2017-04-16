package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Untyped;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UntypedImpl implements Untyped {
    private final SelenideElement impl;

    public UntypedImpl(By by) {
        this.impl = $(by);
    }

    @Override
    public SelenideElement delegate() {
        return impl;
    }
}