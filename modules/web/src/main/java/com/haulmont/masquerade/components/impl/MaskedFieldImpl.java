package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.MaskedField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

public class MaskedFieldImpl extends AbstractComponent<MaskedField> implements MaskedField {
    public MaskedFieldImpl(By by) {
        super(by);
    }

    @Override
    public MaskedField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .click();

        impl.sendKeys(Keys.HOME, value);

        return this;
    }

    @Override
    public String getValue() {
        return impl
                .shouldBe(visible)
                .getValue();
    }

    @Override
    public boolean is(Condition condition) {
        return fieldIs(match(condition), impl, impl)
                .orElse(c -> MaskedField.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), impl, impl)
                .orElse(c -> MaskedField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public MaskedField should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, impl, impl)
                .orElse(c -> MaskedField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public MaskedField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, impl, impl)
                .orElse(c -> MaskedField.super.shouldNot(c)));

        return this;
    }
}