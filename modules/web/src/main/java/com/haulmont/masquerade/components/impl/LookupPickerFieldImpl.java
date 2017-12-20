package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.LookupPickerField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.components.impl.LookupFieldImpl.VAADIN_COMBOBOX_OPTIONLIST;
import static com.haulmont.masquerade.components.impl.LookupFieldImpl.V_FILTERSELECT_BUTTON;
import static com.haulmont.masquerade.sys.TagNames.DIV;
import static com.haulmont.masquerade.sys.VaadinClassNames.disabledClass;

public class LookupPickerFieldImpl extends AbstractInputComponent<LookupPickerField> implements LookupPickerField {
    public LookupPickerFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        return getInputDelegate()
                .shouldBe(visible)
                .getValue();
    }

    @Override
    public LookupPickerField setValue(String value) {
        // todo support textInputAllowed = false
        setFilter(value);

        if (isNullOrEmpty(value)) {
            openOptionsPopup().select(value);
        } else {
            getOptionsPopup().select(value);
        }

        return this;
    }

    @Override
    public LookupPickerField setFilter(String filter) {
        SelenideElement inputImpl = getInputDelegate();

        inputImpl.shouldBe(visible)
                .shouldNotBe(readonly)
                .shouldBe(enabled)
                .click();

        inputImpl.clear();

        if (!isNullOrEmpty(filter)) {
            // todo may be replace with javascript set to speed up this call
            inputImpl.sendKeys(filter);
        }

        return this;
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
    public OptionsPopup<LookupPickerField> openOptionsPopup() {
        $(byChain(by, byClassName(V_FILTERSELECT_BUTTON)))
                .shouldBe(visible)
                .click();

        OptionsPopupImpl<LookupPickerField> optionsPopup = getOptionsPopupElement();
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public OptionsPopup<LookupPickerField> getOptionsPopup() {
        OptionsPopupImpl<LookupPickerField> optionsPopup = getOptionsPopupElement();
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public LookupPickerField closeOptionsPopup() {
        OptionsPopupImpl<LookupPickerField> optionsPopup = getOptionsPopupElement();
        optionsPopup.shouldBe(visible);

        $(byChain(by, DIV))
                .shouldBe(visible)
                .click();

        return this;
    }

    protected OptionsPopupImpl<LookupPickerField> getOptionsPopupElement() {
        return new OptionsPopupImpl<>(VAADIN_COMBOBOX_OPTIONLIST, this);
    }
}