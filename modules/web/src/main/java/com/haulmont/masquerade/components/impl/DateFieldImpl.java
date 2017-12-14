package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.DateField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

public class DateFieldImpl extends AbstractComponent<DateField> implements DateField {

    public DateFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getDateValue() {
        return inputImpl()
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

    @Override
    public DateField setDateValue(String value) {
        inputImpl().shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .click();

        inputImpl().sendKeys(Keys.HOME, value);

        return this;
    }

    protected SelenideElement inputImpl() {
        return $(byChain(by, INPUT));
    }

    @Override
    public boolean is(Condition condition) {
        return fieldIs(match(condition), inputImpl())
                .orElse(c -> DateField.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), inputImpl())
                .orElse(c -> DateField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public DateField should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, inputImpl())
                .orElse(c -> DateField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public DateField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, inputImpl())
                .orElse(c -> DateField.super.shouldNot(c)));

        return this;
    }
}