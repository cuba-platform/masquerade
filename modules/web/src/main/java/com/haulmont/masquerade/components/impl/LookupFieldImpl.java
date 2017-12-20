package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.LookupField;
import com.haulmont.masquerade.conditions.Options;
import com.haulmont.masquerade.conditions.OptionsCount;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.leacox.motif.Motif;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.*;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static org.openqa.selenium.By.className;

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

        OptionsPopupImpl optionsPopup = new OptionsPopupImpl(VAADIN_COMBOBOX_OPTIONLIST);
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public OptionsPopup<LookupField> getOptionsPopup() {
        OptionsPopupImpl optionsPopup = new OptionsPopupImpl(VAADIN_COMBOBOX_OPTIONLIST);
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public LookupField closeOptionsPopup() {
        OptionsPopupImpl optionsPopup = new OptionsPopupImpl(VAADIN_COMBOBOX_OPTIONLIST);
        optionsPopup.shouldBe(visible);

        $(byChain(by, DIV))
                .shouldBe(visible)
                .click();

        return this;
    }

    public class OptionsPopupImpl
            extends AbstractSpecificConditionHandler<OptionsPopup>
            implements OptionsPopup<LookupField> {

        private final By by;
        private final SelenideElement impl;

        public OptionsPopupImpl(By by) {
            this.by = by;
            this.impl = $(by);
        }

        @Override
        public List<String> getVisibleOptions() {
            return $$(byChain(by, TD, SPAN)).texts();
        }

        @Override
        public LookupField select(String option) {
            if (isNullOrEmpty(option)) {
                $(byChain(by, TD, EMPTY_OPTION))
                        .shouldBe(visible)
                        .click();
            } else {
                $(byChain(by, TD, byText(option)))
                        .shouldBe(visible)
                        .click();
            }

            return LookupFieldImpl.this;
        }

        @Override
        public OptionsPopup<LookupField> nextPage() {
            $(byChain(by, className(V_FILTERSELECT_NEXTPAGE)))
                    .shouldBe(visible)
                    .click();
            OptionsPopupImpl optionsPopup = new OptionsPopupImpl(VAADIN_COMBOBOX_OPTIONLIST);
            optionsPopup.shouldBe(visible);
            return optionsPopup;
        }

        @Override
        public boolean hasNextPage() {
            return $(byChain(by, className(V_FILTERSELECT_NEXTPAGE))).isDisplayed();
        }

        @Override
        public OptionsPopup<LookupField> previousPage() {
            $(byChain(by, className(V_FILTERSELECT_PREVPAGE)))
                    .shouldBe(visible)
                    .click();
            OptionsPopupImpl optionsPopup = new OptionsPopupImpl(VAADIN_COMBOBOX_OPTIONLIST);
            optionsPopup.shouldBe(visible);

            return optionsPopup;
        }

        @Override
        public SelenideElement getDelegate() {
            return impl;
        }

        @Override
        public By getBy() {
            return by;
        }

        @SuppressWarnings("CodeBlock2Expr")
        @Override
        public boolean apply(SpecificCondition condition) {
            return Motif.match(condition)
                    .when(hasType(Options.class)).get(opts -> {
                        List<String> options = opts.getOptions().stream()
                                .map(o -> isNullOrEmpty(o) ? EMPTY_OPTION_VALUE : o)
                                .collect(Collectors.toList());

                        List<String> texts = $$(byChain(by, TD, SPAN)).texts();
                        return texts.equals(options);
                    })
                    .when(hasType(OptionsCount.class)).get(optsCount -> {
                        return $$(byChain(by, TD, SPAN)).size() == optsCount.getCount();
                    })
                    .getMatch();
        }
    }
}