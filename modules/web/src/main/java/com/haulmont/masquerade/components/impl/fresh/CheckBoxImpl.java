package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.components.CheckBox;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static org.openqa.selenium.By.tagName;

public class CheckBoxImpl implements CheckBox {
    private By by;
    private SelenideElement impl;

    public CheckBoxImpl(By by) {
        this.by = by;
        this.impl = $(by);
    }

    @Override
    public boolean is(Condition condition) {
        // todo Conditions.enabled
        // todo Conditions.editable
        // todo Conditions.checked
        // todo Conditions.selected
        return CheckBox.super.is(condition);
    }

    @Override
    public boolean has(Condition condition) {
        if (condition instanceof Conditions.Caption) {
            String expectedCaption = ((Conditions.Caption) condition).getCaption();
            return Objects.equals(getCaption(), expectedCaption);
        }
        return CheckBox.super.has(condition);
    }

    @Override
    public CheckBox should(Condition... condition) {
        // todo
        return CheckBox.super.should(condition);
    }

    @Override
    public CheckBox shouldNot(Condition... condition) {
        // todo
        return CheckBox.super.shouldNot(condition);
    }

    @Override
    public void set() {
        $(byChain(by, tagName("input")))
                .shouldBe(visible)
                .shouldBe(enabled)
                .setSelected(true);
    }

    @Override
    public void unset() {
        $(byChain(by, tagName("input")))
                .shouldBe(visible)
                .shouldBe(enabled)
                .setSelected(true);
    }

    @Override
    public boolean isSet() {
        return $(byChain(by, tagName("input")))
                .is(Conditions.checked);
    }

    @Override
    public String getCaption() {
        return $(byChain(by, tagName("label"))).getText();
    }

    @Override
    public boolean isEnabled() {
        return is(enabled);
    }

    @Override
    public SelenideElement delegate() {
        return impl;
    }

    @Override
    public boolean isEditable() {
        return is(Conditions.editable);
    }
}