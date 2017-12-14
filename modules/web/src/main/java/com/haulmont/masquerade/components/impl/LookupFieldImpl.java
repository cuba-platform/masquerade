package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.LookupField;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Strings.nullToEmpty;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.*;
import static com.haulmont.masquerade.sys.VaadinClassNames.requiredClass;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.className;

public class LookupFieldImpl extends AbstractComponent<LookupField> implements LookupField {
    public static final String EMPTY_OPTION_VALUE = "\u00a0";

    public static final String V_FILTERSELECT_NEXTPAGE = "v-filterselect-nextpage";
    public static final String V_FILTERSELECT_PREVPAGE = "v-filterselect-prevpage";

    public static final By VAADIN_COMBOBOX_OPTIONLIST = By.id("VAADIN_COMBOBOX_OPTIONLIST");
    public static final By EMPTY_OPTION = byText(EMPTY_OPTION_VALUE);

    public LookupFieldImpl(By by) {
        super(by);
    }

    protected SelenideElement inputImpl() {
        return $(byChain(by, INPUT));
    }

    @Override
    public String getValue() {
        return inputImpl()
                .shouldBe(visible)
                .shouldBe(enabled)
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
        SelenideElement inputImpl = inputImpl();

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
    public boolean is(Condition condition) {
        return match(condition)
                .when(isRequired()).get(() ->
                        inputImpl().has(requiredClass)
                )
                .orElse(c -> LookupField.super.has(condition))
                .getMatch();
    }

    @Override
    public boolean has(Condition condition) {
        return match(condition)
                .when(isValue()).get(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    return inputImpl()
                            .has(exactValue(expectedValue));
                })
                .orElse(c -> LookupField.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public LookupField should(Condition... conditions) {
        matchAll(conditions, m -> m
                .when(isValue()).then(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    inputImpl()
                            .shouldBe(visible)
                            .shouldNotHave(exactValue(expectedValue));
                })
                .when(isRequired()).then(() -> {
                    inputImpl().shouldHave(requiredClass);
                })
                .orElse(c -> LookupField.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public LookupField shouldNot(Condition... conditions) {
        matchAll(conditions, m -> m
                .when(isValue()).then(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    inputImpl()
                            .shouldBe(visible)
                            .shouldHave(exactValue(expectedValue));
                })
                .when(isRequired()).then(() -> {
                    inputImpl().shouldNotHave(requiredClass);
                })
                .orElse(c -> LookupField.super.shouldNot(c)));

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
        public OptionsPopup previousPage() {
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
        public OptionsPopup should(Condition... conditions) {
            matchAll(conditions, m -> m
                    .when(isOptions()).then(opts -> {
                        List<String> options = opts.getOptions().stream()
                                .map(o -> isNullOrEmpty(o) ? EMPTY_OPTION_VALUE : o)
                                .collect(Collectors.toList());

                        $$(byChain(by, TD, SPAN))
                                .shouldHave(texts(options));
                    })
                    .when(isOptionsCount()).then(optsCount -> {
                        $$(byChain(by, TD, SPAN))
                                .shouldHaveSize(optsCount.getCount());
                    })
                    .orElse(c -> OptionsPopup.super.should(c)));

            return this;
        }
    }
}