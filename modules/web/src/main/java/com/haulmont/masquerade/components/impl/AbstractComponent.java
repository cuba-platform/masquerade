package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Component;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class AbstractComponent<T extends Component> implements Component<T> {
    protected final By by;
    protected final SelenideElement impl;

    protected AbstractComponent(By by) {
        this.by = by;
        this.impl = $(by);
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