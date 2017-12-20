package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.PickerField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.TagNames.DIV;
import static com.haulmont.masquerade.sys.VaadinClassNames.disabledClass;

public class PickerFieldImpl extends AbstractInputComponent<PickerField> implements PickerField {
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
        return getInputDelegate()
                .shouldBe(visible)
                .getValue();
    }
}