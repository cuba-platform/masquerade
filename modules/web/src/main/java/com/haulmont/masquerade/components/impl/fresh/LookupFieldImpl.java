package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.collections.Texts;
import com.haulmont.masquerade.components.LookupField;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.Selectors.byChain;
import static org.openqa.selenium.By.tagName;

public class LookupFieldImpl implements LookupField {
    public static final String VAADIN_COMBOBOX_OPTIONLIST = "VAADIN_COMBOBOX_OPTIONLIST";

    private final By by;
    private final SelenideElement impl;

    public LookupFieldImpl(By by) {
        this.by = by;
        this.impl = $(by);
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public String getValue() {
        return $(byChain(by, tagName("input")))
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .getValue();
    }

    @Override
    public LookupField setValue(String value) {
        setFilter(value);

        $(byChain(by, tagName("input"))).pressEnter();

        return this;
    }

    @Override
    public LookupField setFilter(String filter) {
        // todo may be replace with javascript set to speed up this call
        $(byChain(by, tagName("input")))
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldBe(editable)
                .setValue(filter);

        return this;
    }

    @Override
    public OptionsPopup openOptionsPopup() {
        $(byChain(by, tagName("div")))
                .shouldBe(visible)
                .click();

        OptionsPopupImpl optionsPopup = new OptionsPopupImpl(By.id(VAADIN_COMBOBOX_OPTIONLIST));
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public OptionsPopup getOptionsPopup() {
        OptionsPopupImpl optionsPopup = new OptionsPopupImpl(By.id(VAADIN_COMBOBOX_OPTIONLIST));
        optionsPopup.shouldBe(visible);

        return optionsPopup;
    }

    @Override
    public LookupField closeOptionsPopup() {
        OptionsPopupImpl optionsPopup = new OptionsPopupImpl(By.id(VAADIN_COMBOBOX_OPTIONLIST));
        optionsPopup.shouldBe(visible);

        $(byChain(by, tagName("div")))
                .shouldBe(visible)
                .click();

        return this;
    }

    @Override
    public LookupField should(Condition... condition) {
        // todo
        return LookupField.super.should(condition);
    }

    @Override
    public LookupField shouldNot(Condition... condition) {
        // todo
        return LookupField.super.shouldNot(condition);
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
            return $$(byChain(by, tagName("td"), tagName("span"))).texts();
        }

        @Override
        public LookupField select(String option) {
            $(byChain(by, tagName("td"), byText(option)))
                    .shouldBe(visible)
                    .click();

            return LookupFieldImpl.this;
        }

        @Override
        public OptionsPopup nextPage() {
            // todo implement
            throw new UnsupportedOperationException();
        }

        @Override
        public OptionsPopup previousPage() {
            // todo implement
            throw new UnsupportedOperationException();
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
                    Texts texts = new Texts(((Options) c).getOptions());
                    $$(byChain(by, tagName("td"), tagName("span")))
                            .shouldHave(texts);
                } else if (c instanceof OptionsCount) {
                    int count = ((OptionsCount) c).getCount();
                    $$(byChain(by, tagName("td"), tagName("span")))
                            .shouldHaveSize(count);
                } else {
                    OptionsPopup.super.should(c);
                }
            }
            return this;
        }
    }
}