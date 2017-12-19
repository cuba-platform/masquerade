package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.Component;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.haulmont.masquerade.conditions.SpecificConditionContext;
import com.haulmont.masquerade.conditions.SpecificConditionHandler;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.leacox.motif.Motif.match;

@SuppressWarnings("unchecked")
public abstract class AbstractComponent<T extends Component> implements Component<T>, SpecificConditionHandler {
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

    @Override
    public boolean is(Condition condition) {
        return SpecificConditionContext.get(this, () ->
                getDelegate().is(condition)
        );
    }

    @Override
    public boolean has(Condition condition) {
        return SpecificConditionContext.get(this, () ->
                getDelegate().has(condition)
        );
    }

    @Override
    public T should(Condition... conditions) {
        SpecificConditionContext.with(this, () ->
                getDelegate().should(conditions)
        );
        return (T) this;
    }

    @Override
    public T shouldNot(Condition... conditions) {
        SpecificConditionContext.with(this, () ->
                getDelegate().shouldNot(conditions)
        );
        return (T) this;
    }

    @Override
    public T waitUntil(Condition condition, long timeoutMilliseconds) {
        SpecificConditionContext.with(this, () ->
                getDelegate().waitUntil(condition, timeoutMilliseconds)
        );
        return (T) this;
    }

    @Override
    public T waitUntil(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        SpecificConditionContext.with(this, () ->
                getDelegate().waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds)
        );
        return (T) this;
    }

    @Override
    public T waitWhile(Condition condition, long timeoutMilliseconds) {
        SpecificConditionContext.with(this, () ->
                getDelegate().waitWhile(condition, timeoutMilliseconds)
        );
        return (T) this;
    }

    @Override
    public T waitWhile(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        SpecificConditionContext.with(this, () ->
                getDelegate().waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds)
        );
        return (T) this;
    }
}