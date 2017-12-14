package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.TimeField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

public class TimeFieldImpl extends AbstractComponent<TimeField> implements TimeField {
    public TimeFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        return impl
                .shouldBe(visible)
                .getValue();
    }

    @Override
    public TimeField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .click();

        impl.sendKeys(Keys.HOME, value);
        return this;
    }

    @Override
    public boolean is(Condition condition) {
        return fieldIs(match(condition), impl)
                .orElse(c -> TimeField.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), impl)
                .orElse(c -> TimeField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public TimeField should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, impl)
                .orElse(c -> TimeField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public TimeField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, impl)
                .orElse(c -> TimeField.super.shouldNot(c)));

        return this;
    }
}