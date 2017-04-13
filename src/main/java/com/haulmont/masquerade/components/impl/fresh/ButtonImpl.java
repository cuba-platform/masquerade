package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.Button;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static org.openqa.selenium.By.className;

public class ButtonImpl implements Button {
    private final By by;

    public ButtonImpl(By by) {
        this.by = by;
    }

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
    public void click() {
        $(by).shouldBe(visible).click();
    }
}