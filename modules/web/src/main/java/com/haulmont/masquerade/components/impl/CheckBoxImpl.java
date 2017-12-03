package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.CheckBox;
import com.haulmont.masquerade.conditions.Caption;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;
import static com.haulmont.masquerade.sys.TagNames.LABEL;
import static com.haulmont.masquerade.sys.VaadinClassNames.DISABLED_CLASSNAME;
import static com.haulmont.masquerade.sys.VaadinClassNames.READONLY_CLASSNAME;

public class CheckBoxImpl extends AbstractComponent<CheckBox> implements CheckBox {

    public CheckBoxImpl(By by) {
        super(by);
    }

    @Override
    public boolean is(Condition condition) {
        if (condition == enabled) {
            return !impl.has(cssClass(DISABLED_CLASSNAME));
        }
        if (condition == disabled) {
            return impl.has(cssClass(DISABLED_CLASSNAME));
        }
        if (condition == checked || condition == selected) {
            return $(byChain(by, INPUT)).is(condition);
        }
        if (condition == editable) {
            return !impl.has(cssClass(READONLY_CLASSNAME));
        }
        if (condition == readonly) {
            return impl.has(cssClass(READONLY_CLASSNAME));
        }
        return CheckBox.super.is(condition);
    }

    @Override
    public boolean has(Condition condition) {
        if (condition instanceof Caption) {
            String expectedCaption = ((Caption) condition).getCaption();
            return Objects.equals(getCaption(), expectedCaption);
        }
        return CheckBox.super.has(condition);
    }

    @Override
    public CheckBox should(Condition... condition) {
        for (Condition c : condition) {
            if (c == enabled) {
                impl.shouldNotHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == disabled) {
                impl.shouldHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == checked || c == selected) {
                $(byChain(by, INPUT)).shouldBe(c);
            } else if (c == editable) {
                impl.shouldNotHave(cssClass(READONLY_CLASSNAME));
            } else if (c == readonly) {
                impl.shouldHave(cssClass(READONLY_CLASSNAME));
            } else if (c instanceof Caption) {
                String caption = ((Caption) c).getCaption();
                $(byChain(by, LABEL)).shouldHave(exactTextCaseSensitive(caption));
            } else {
                CheckBox.super.should(c);
            }
        }
        return this;
    }

    @Override
    public CheckBox shouldNot(Condition... condition) {
        for (Condition c : condition) {
            if (c == enabled) {
                impl.shouldHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == disabled) {
                impl.shouldNotHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == checked || c == selected) {
                $(byChain(by, INPUT)).shouldNotBe(c);
            } else if (c == editable) {
                impl.shouldHave(cssClass(READONLY_CLASSNAME));
            } else if (c == readonly) {
                impl.shouldNotHave(cssClass(READONLY_CLASSNAME));
            } else if (c instanceof Caption) {
                String caption = ((Caption) c).getCaption();
                $(byChain(by, LABEL)).shouldNotHave(exactTextCaseSensitive(caption));
            } else {
                CheckBox.super.shouldNot(c);
            }
        }
        return this;
    }

    @Override
    public CheckBox setChecked(boolean checked) {
        SelenideElement checkBoxInput = $(byChain(by, INPUT))
                .shouldBe(visible)
                .shouldBe(enabled);

        if (checked != checkBoxInput.is(Condition.checked)) {
            checkBoxInput.sendKeys(" ");
        }

        return this;
    }

    @Override
    public boolean isChecked() {
        return $(byChain(by, INPUT)).is(checked);
    }

    @Override
    public String getCaption() {
        return $(byChain(by, LABEL)).getText();
    }
}