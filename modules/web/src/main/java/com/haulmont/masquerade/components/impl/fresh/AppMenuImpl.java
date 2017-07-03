package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.AppMenu;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byCubaId;

public class AppMenuImpl extends AbstractComponent<AppMenu> implements AppMenu {

    public AppMenuImpl(By by) {
        super(by);
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
}