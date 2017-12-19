package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.LookupPickerField;
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

public class LookupPickerFieldImpl extends AbstractComponent<LookupPickerField> implements LookupPickerField {
    public LookupPickerFieldImpl(By by) {
        super(by);
    }

    protected SelenideElement inputImpl() {
        return $(byChain(by, INPUT));
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
    public OptionsPopup<LookupPickerField> getOptionsPopup() {
        return null;
    }

    @Override
    public OptionsPopup<LookupPickerField> openOptionsPopup() {
        return null;
    }

    @Override
    public LookupPickerField closeOptionsPopup() {
        return null;
    }

    @Override
    public boolean is(Condition condition) {
        return fieldIs(match(condition), impl, inputImpl())
                .orElse(c -> LookupPickerField.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return fieldHas(match(condition), impl, inputImpl())
                .orElse(c -> LookupPickerField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public LookupPickerField should(Condition... conditions) {
        matchAll(conditions, m -> fieldShould(m, impl, inputImpl())
                .orElse(c -> LookupPickerField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public LookupPickerField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> fieldShouldNot(m, impl, inputImpl())
                .orElse(c -> LookupPickerField.super.shouldNot(c)));

        return this;
    }
}