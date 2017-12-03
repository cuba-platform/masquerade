package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.Label;
import com.haulmont.masquerade.conditions.Value;
import com.haulmont.masquerade.conditions.ValueContains;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.haulmont.masquerade.sys.VaadinClassNames.DISABLED_CLASSNAME;

public class LabelImpl extends AbstractComponent<Label> implements Label {
    public LabelImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        return impl.getText();
    }

    @Override
    public boolean is(Condition condition) {
        if (condition == Condition.enabled) {
            return !impl.has(cssClass(DISABLED_CLASSNAME));
        }
        if (condition == Condition.disabled) {
            return impl.has(cssClass(DISABLED_CLASSNAME));
        }

        return Label.super.is(condition);
    }

    @Override
    public boolean has(Condition condition) {
        if (condition instanceof Value) {
            String value = ((Value) condition).getExpectedValue();
            return impl.has(exactTextCaseSensitive(value));
        }
        if (condition instanceof ValueContains) {
            String value = ((ValueContains) condition).getExpectedValueSubstring();
            return impl.has(text(value));
        }

        return Label.super.has(condition);
    }

    @Override
    public Label should(Condition... condition) {
        for (Condition c : condition) {
            if (c == enabled) {
                impl.shouldNotHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == disabled) {
                impl.shouldHave(cssClass(DISABLED_CLASSNAME));
            } else if (c instanceof Value) {
                String value = ((Value) c).getExpectedValue();
                impl.shouldHave(exactTextCaseSensitive(value));
            } else if (c instanceof ValueContains) {
                String value = ((ValueContains) c).getExpectedValueSubstring();
                impl.shouldHave(text(value));
            } else {
                Label.super.should(c);
            }
        }
        return this;
    }

    @Override
    public Label shouldNot(Condition... condition) {
        for (Condition c : condition) {
            if (c == enabled) {
                impl.shouldHave(cssClass(DISABLED_CLASSNAME));
            } else if (c == disabled) {
                impl.shouldNotHave(cssClass(DISABLED_CLASSNAME));
            } else if (c instanceof Value) {
                String value = ((Value) c).getExpectedValue();
                impl.shouldNotHave(exactTextCaseSensitive(value));
            } else if (c instanceof ValueContains) {
                String value = ((ValueContains) c).getExpectedValueSubstring();
                impl.shouldNotHave(text(value));
            } else {
                Label.super.shouldNot(c);
            }
        }
        return this;
    }
}