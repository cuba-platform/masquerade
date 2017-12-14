package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.TextArea;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.TEXTAREA;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

public class TextAreaImpl extends AbstractComponent<TextArea> implements TextArea {

    public TextAreaImpl(By by) {
        super(by);
    }

    @Override
    public TextArea setValue(String value) {
        inputImpl().shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .setValue(value);
        return this;
    }

    @Override
    public String getValue() {
        return inputImpl().shouldBe(visible).getValue();
    }

    protected SelenideElement inputImpl() {
        return $(byChain(by, TEXTAREA));
    }

    @Override
    public boolean is(Condition condition) {
        return fieldIs(match(condition), inputImpl())
                .orElse(c -> TextArea.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), inputImpl())
                .orElse(c -> TextArea.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public TextArea should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, inputImpl())
                .orElse(c -> TextArea.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public TextArea shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, inputImpl())
                .orElse(c -> TextArea.super.shouldNot(c)));

        return this;
    }
}