package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.LookupPickerField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.visible;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.TagNames.DIV;
import static com.haulmont.masquerade.sys.VaadinClassNames.disabledClass;

public class LookupPickerFieldImpl extends AbstractInputComponent<LookupPickerField> implements LookupPickerField {
    public LookupPickerFieldImpl(By by) {
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
}