package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.Label;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.haulmont.masquerade.conditions.Value;
import com.haulmont.masquerade.conditions.ValueContains;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Condition.text;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.Motif.match;

public class LabelImpl extends AbstractComponent<Label> implements Label {
    public LabelImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        return impl.getText();
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .when(hasType(Value.class)).get(v ->
                        impl.has(exactTextCaseSensitive(v.getExpectedValue()))
                )
                .when(hasType(ValueContains.class)).get(v ->
                        impl.has(text(v.getExpectedValueSubstring()))
                )
                .getMatch();
    }
}