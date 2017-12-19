package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Component;
import com.haulmont.masquerade.conditions.SpecificCondition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.fieldApply;
import static com.leacox.motif.Motif.match;

public class AbstractInputComponent<T extends Component> extends AbstractComponent<T> {
    protected AbstractInputComponent(By by) {
        super(by);
    }

    protected SelenideElement getInputDelegate() {
        return $(byChain(by, INPUT));
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return fieldApply(match(condition), getDelegate(), getInputDelegate())
                .getMatch();
    }
}