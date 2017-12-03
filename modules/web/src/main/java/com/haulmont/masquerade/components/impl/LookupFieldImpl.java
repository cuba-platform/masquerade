package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.collections.Texts;
import com.haulmont.masquerade.components.LookupField;
import com.haulmont.masquerade.conditions.Options;
import com.haulmont.masquerade.conditions.OptionsCount;
import com.haulmont.masquerade.conditions.Value;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.*;

public class LookupFieldImpl extends AbstractComponent<LookupField> implements LookupField {
    public static final String EMPTY_OPTION_VALUE = "\u00a0";

    public static final String V_FILTERSELECT_NEXTPAGE = "v-filterselect-nextpage";
    public static final String V_FILTERSELECT_PREVPAGE = "v-filterselect-prevpage";

    public static final By VAADIN_COMBOBOX_OPTIONLIST = By.id("VAADIN_COMBOBOX_OPTIONLIST");
    public static final By EMPTY_OPTION = byText(EMPTY_OPTION_VALUE);

    public LookupFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        return $(byChain(by, INPUT))
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
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
        SelenideElement inputImpl = $(byChain(by, INPUT));

        inputImpl.shouldBe(visible)
                .shouldBe(editable)
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
    public OptionsPopup openOptionsPopup() {
        $(byChain(by, DIV))
                .shouldBe(visible)
                .click();

        OptionsPopupImpl optionsPopup = new OptionsPopupImpl(VAADIN_COMBOBOX_OPTIONLIST);
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public OptionsPopup getOptionsPopup() {
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

    @Override
    public boolean has(Condition condition) {
        if (condition instanceof Value) {
            String expectedValue = ((Value) condition).getExpectedValue();
            if (isNullOrEmpty(expectedValue)) {
                expectedValue = "";
            }

            return $(byChain(by, INPUT))
                    .has(exactValue(expectedValue));
        }
        return LookupField.super.has(condition);
    }

    @Override
    public LookupField should(Condition... condition) {
        for (Condition c : condition) {
            if (c instanceof Value) {
                String expectedValue = ((Value) c).getExpectedValue();
                if (isNullOrEmpty(expectedValue)) {
                    expectedValue = "";
                }

                $(byChain(by, INPUT))
                        .shouldBe(visible)
                        .shouldHave(exactValue(expectedValue));
            } else {
                LookupField.super.should(c);
            }
        }
        return this;
    }

    @Override
    public LookupField shouldNot(Condition... condition) {
        for (Condition c : condition) {
            if (c instanceof Value) {
                String expectedValue = ((Value) c).getExpectedValue();
                if (isNullOrEmpty(expectedValue)) {
                    expectedValue = "";
                }

                $(byChain(by, INPUT))
                        .shouldBe(visible)
                        .shouldNotHave(exactValue(expectedValue));
            } else {
                LookupField.super.shouldNot(c);
            }
        }
        return this;
    }

    public class OptionsPopupImpl implements OptionsPopup {
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
        public OptionsPopup nextPage() {
            $(byChain(by, By.className(V_FILTERSELECT_NEXTPAGE)))
                    .shouldBe(visible)
                    .click();
            OptionsPopupImpl optionsPopup = new OptionsPopupImpl(VAADIN_COMBOBOX_OPTIONLIST);
            optionsPopup.shouldBe(visible);
            return optionsPopup;
        }

        @Override
        public boolean hasNextPage() {
            return $(byChain(by, By.className(V_FILTERSELECT_NEXTPAGE))).isDisplayed();
        }

        @Override
        public OptionsPopup previousPage() {
            $(byChain(by, By.className(V_FILTERSELECT_PREVPAGE)))
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

        @Override
        public OptionsPopup should(Condition... condition) {
            for (Condition c : condition) {
                if (c instanceof Options) {
                    List<String> options = ((Options) c).getOptions().stream()
                            .map(o -> isNullOrEmpty(o) ? EMPTY_OPTION_VALUE : o)
                            .collect(Collectors.toList());

                    Texts texts = new Texts(options);
                    $$(byChain(by, TD, SPAN))
                            .shouldHave(texts);
                } else if (c instanceof OptionsCount) {
                    int count = ((OptionsCount) c).getCount();
                    $$(byChain(by, TD, SPAN))
                            .shouldHaveSize(count);
                } else {
                    OptionsPopup.super.should(c);
                }
            }
            return this;
        }
    }
}