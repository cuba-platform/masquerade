package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.TextField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Conditions.enabled;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

public class TextFieldImpl extends AbstractComponent<TextField> implements TextField {

    public TextFieldImpl(By by) {
        super(by);
    }

    @Override
    public TextField setValue(String value) {
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
        return fieldIs(match(condition), impl, impl)
                .orElse(c -> TextField.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), impl, impl)
                .orElse(c -> TextField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public TextField should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, impl, impl)
                .orElse(c -> TextField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public TextField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, impl, impl)
                .orElse(c -> TextField.super.shouldNot(c)));

        return this;
    }
}