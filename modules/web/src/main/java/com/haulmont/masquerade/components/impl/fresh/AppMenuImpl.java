package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.AppMenu;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byCubaId;

public class AppMenuImpl implements AppMenu {
    private final By by;
    private final SelenideElement impl;

    public AppMenuImpl(By by) {
        this.by = by;
        this.impl = $(by);
    }

    @Override
    public <T> T openItem(Class<T> clazz, String... path) {
        for (String s : path) {
            $(byCubaId(s)).shouldBe(visible).click();
        }
        return Components.wire(clazz);
    }

    @Override
    public void openItem(String... path) {
        for (String s : path) {
            $(byCubaId(s)).shouldBe(visible).click();
        }
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