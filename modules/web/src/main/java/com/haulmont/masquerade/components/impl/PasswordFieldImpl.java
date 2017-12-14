package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.PasswordField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Conditions.enabled;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

public class PasswordFieldImpl extends AbstractComponent<PasswordField> implements PasswordField {

    public PasswordFieldImpl(By by) {
        super(by);
    }

    @Override
    public PasswordField setValue(String value) {
        impl.shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .setValue(value);
        return this;
    }

    @Override
    public String getValue() {
        return impl.shouldBe(visible).getValue();
    }

    @Override
    public boolean is(Condition condition) {
        return fieldIs(match(condition), impl)
                .orElse(c -> PasswordField.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), impl)
                .orElse(c -> PasswordField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public PasswordField should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, impl)
                .orElse(c -> PasswordField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public PasswordField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, impl)
                .orElse(c -> PasswordField.super.shouldNot(c)));

        return this;
    }
}