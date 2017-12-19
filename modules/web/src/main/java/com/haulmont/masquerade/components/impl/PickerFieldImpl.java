package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.PickerField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.visible;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.TagNames.DIV;
import static com.haulmont.masquerade.sys.TagNames.INPUT;
import static com.haulmont.masquerade.sys.VaadinClassNames.disabledClass;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

public class PickerFieldImpl extends AbstractComponent<PickerField> implements PickerField {
    public PickerFieldImpl(By by) {
        super(by);
    }

    @Override
    public void triggerAction(Action action) {
        $(byChain(by, DIV, byCubaId(action.getId())))
                .shouldBe(visible)
                .shouldNotHave(disabledClass)
                .click();
    }

    @Override
    public <T> T triggerAction(Class<T> clazz, Action action) {
        triggerAction(action);
        return Components.wire(clazz);
    }

    @Override
    public String getValue() {
        return inputImpl()
                .shouldBe(visible)
                .getValue();
    }

    protected SelenideElement inputImpl() {
        return $(byChain(by, INPUT));
    }

    @Override
    public boolean is(Condition condition) {
        return fieldIs(match(condition), impl, inputImpl())
                .orElse(c -> PickerField.super.is(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), impl, inputImpl())
                .orElse(c -> PickerField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public PickerField should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, impl, inputImpl())
                .orElse(c -> PickerField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public PickerField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, impl, inputImpl())
                .orElse(c -> PickerField.super.shouldNot(c)));

        return this;
    }
}