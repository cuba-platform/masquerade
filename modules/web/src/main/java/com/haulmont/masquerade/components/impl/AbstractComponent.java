package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Component;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.haulmont.masquerade.conditions.SpecificConditionHandler;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.leacox.motif.Motif.match;

@SuppressWarnings("unchecked")
public abstract class AbstractComponent<T extends Component>
        extends AbstractSpecificConditionHandler<T>
        implements Component<T>, SpecificConditionHandler {

    protected final By by;
    protected final SelenideElement impl;

    protected AbstractComponent(By by) {
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
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .getMatch();
    }
}