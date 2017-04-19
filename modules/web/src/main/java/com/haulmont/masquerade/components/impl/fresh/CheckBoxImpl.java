package com.haulmont.masquerade.components.impl.fresh;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.components.CheckBox;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.VaadinClassNames.DISABLED_CLASSNAME;
import static com.haulmont.masquerade.sys.VaadinClassNames.READONLY_CLASSNAME;
import static org.openqa.selenium.By.tagName;

public class CheckBoxImpl implements CheckBox {
    private By by;
    private SelenideElement impl;

    public CheckBoxImpl(By by) {
        this.by = by;
        this.impl = $(by);
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
            return $(byChain(by, tagName("input"))).is(condition);
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
        if (condition instanceof Conditions.Caption) {
            String expectedCaption = ((Conditions.Caption) condition).getCaption();
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
                $(byChain(by, tagName("input"))).shouldBe(c);
            } else if (c == editable) {
                impl.shouldNotHave(cssClass(READONLY_CLASSNAME));
            } else if (c == readonly) {
                impl.shouldHave(cssClass(READONLY_CLASSNAME));
            } else if (c instanceof Conditions.Caption) {
                String caption = ((Conditions.Caption) c).getCaption();
                $(byChain(by, tagName("label"))).shouldHave(exactTextCaseSensitive(caption));
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
                $(byChain(by, tagName("input"))).shouldNotBe(c);
            } else if (c == editable) {
                impl.shouldHave(cssClass(READONLY_CLASSNAME));
            } else if (c == readonly) {
                impl.shouldNotHave(cssClass(READONLY_CLASSNAME));
            } else if (c instanceof Conditions.Caption) {
                String caption = ((Conditions.Caption) c).getCaption();
                $(byChain(by, tagName("label"))).shouldNotHave(exactTextCaseSensitive(caption));
            } else {
                CheckBox.super.shouldNot(c);
            }
        }
        return this;
    }

    @Override
    public CheckBox setChecked(boolean checked) {
        $(byChain(by, tagName("input")))
                .shouldBe(visible)
                .shouldBe(enabled)
                .setSelected(checked);
        return this;
    }

    @Override
    public boolean isChecked() {
        return $(byChain(by, tagName("input"))).is(checked);
    }

    @Override
    public String getCaption() {
        return $(byChain(by, tagName("label"))).getText();
    }

    @Override
    public boolean isEnabled() {
        return is(enabled);
    }

    @Override
    public SelenideElement getDelegate() {
        return impl;
    }

    @Override
    public boolean isEditable() {
        return is(editable);
    }

    @Override
    public By getBy() {
        return by;
    }
}