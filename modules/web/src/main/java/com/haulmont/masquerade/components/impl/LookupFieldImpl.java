package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.LookupField;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.DIV;

public class LookupFieldImpl extends AbstractInputComponent<LookupField> implements LookupField {
    public static final String EMPTY_OPTION_VALUE = "\u00a0";

    public static final String V_FILTERSELECT_NEXTPAGE = "v-filterselect-nextpage";
    public static final String V_FILTERSELECT_PREVPAGE = "v-filterselect-prevpage";

    public static final String V_FILTERSELECT_BUTTON = "v-filterselect-button";

    public static final By VAADIN_COMBOBOX_OPTIONLIST = By.id("VAADIN_COMBOBOX_OPTIONLIST");
    public static final By EMPTY_OPTION = byText(EMPTY_OPTION_VALUE);

    public LookupFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        return getInputDelegate()
                .shouldBe(visible)
                .getValue();
    }

    @Override
    public LookupField setValue(String value) {
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
    public LookupField setFilter(String filter) {
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
    public OptionsPopup<LookupField> openOptionsPopup() {
        $(byChain(by, byClassName(V_FILTERSELECT_BUTTON)))
                .shouldBe(visible)
                .click();

        OptionsPopupImpl<LookupField> optionsPopup = getOptionsPopupElement();
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public OptionsPopup<LookupField> getOptionsPopup() {
        OptionsPopupImpl<LookupField> optionsPopup = getOptionsPopupElement();
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public LookupField closeOptionsPopup() {
        OptionsPopupImpl<LookupField> optionsPopup = getOptionsPopupElement();
        optionsPopup.shouldBe(visible);

        $(byChain(by, DIV))
                .shouldBe(visible)
                .click();

        return this;
    }

    protected OptionsPopupImpl<LookupField> getOptionsPopupElement() {
        return new OptionsPopupImpl<>(VAADIN_COMBOBOX_OPTIONLIST, this);
    }
}