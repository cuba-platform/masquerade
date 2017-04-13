package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Button;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static org.openqa.selenium.By.className;

public class ButtonImpl implements Button {
    private final By by;
    private final SelenideElement impl;

    public ButtonImpl(By by) {
        this.by = by;
        this.impl = $(by);
    }

    // todo implement support for shouldBe(Condition.enabled)

    @Override
    public String getCaption() {
        // todo test
        return $(byChain(by, className(".v-caption-text"))).getText();
    }

    @Override
    public boolean isEnabled() {
        // todo implement
        return false;
    }

    @Override
    public boolean isVisible() {
        return is(Condition.visible);
    }

    @Override
    public void click() {
        impl.shouldBe(visible).click();
    }

    @Override
    public SelenideElement delegate() {
        return impl;
    }
}