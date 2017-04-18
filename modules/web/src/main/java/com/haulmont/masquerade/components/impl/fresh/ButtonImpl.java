package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.components.Button;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.VaadinClassNames.DISABLED_CLASSNAME;
import static org.openqa.selenium.By.className;

public class ButtonImpl implements Button {

    public static final String BUTTON_CAPTION_CLASSNAME = "v-button-caption";

    private final By by;
    private final SelenideElement impl;

    public ButtonImpl(By by) {
        this.by = by;
        this.impl = $(by);
    }

    @Override
    public String getCaption() {
        return $(byChain(by, className(BUTTON_CAPTION_CLASSNAME))).getText();
    }

    @Override
    public boolean is(Condition c) {
        if (c == enabled) {
            return !impl.has(cssClass(DISABLED_CLASSNAME));
        }
        if (c == disabled) {
            return impl.has(cssClass(DISABLED_CLASSNAME));
        }
        return Button.super.is(c);
    }

    @Override
    public boolean has(Condition condition) {
        if (condition instanceof Conditions.Caption) {
            String expectedCaption = ((Conditions.Caption) condition).getCaption();
            return Objects.equals(getCaption(), expectedCaption);
        }
        return Button.super.has(condition);
    }

    @Override
    public boolean isEnabled() {
        return is(enabled);
    }

    @Override
    public void click() {
        impl.shouldBe(visible)
                .shouldNotHave(cssClass(DISABLED_CLASSNAME))
                .click();
    }

    @Override
    public Button should(Condition... condition) {
        for (Condition c : condition) {
            if (c == enabled) {
                impl.shouldNotHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == disabled) {
                impl.shouldHave(cssClass(DISABLED_CLASSNAME));
            } else if (c instanceof Conditions.Caption) {
                String caption = ((Conditions.Caption) c).getCaption();
                $(byChain(by, className(BUTTON_CAPTION_CLASSNAME))).shouldHave(exactTextCaseSensitive(caption));
            } else {
                Button.super.should(c);
            }
        }
        return this;
    }

    @Override
    public Button shouldNot(Condition... condition) {
        for (Condition c : condition) {
            if (c == enabled) {
                impl.shouldHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == disabled) {
                impl.shouldNotHave(cssClass(DISABLED_CLASSNAME));
            } if (c instanceof Conditions.Caption) {
                String caption = ((Conditions.Caption) c).getCaption();
                $(byChain(by, className(BUTTON_CAPTION_CLASSNAME))).shouldNotHave(exactTextCaseSensitive(caption));
            } else {
                Button.super.shouldNot(c);
            }
        }
        return this;
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public By getBy() {
        return by;
    }
}