package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.base.SelenideElementWrapper;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.haulmont.masquerade.conditions.SpecificConditionContext;
import com.haulmont.masquerade.conditions.SpecificConditionHandler;
import com.leacox.motif.MatchException;

@SuppressWarnings("unchecked")
public abstract class AbstractSpecificConditionHandler<T>
        implements SpecificConditionHandler, SelenideElementWrapper<T> {

    @Override
    public boolean apply(SpecificCondition condition) {
        throw new MatchException("Unsupported SpecificCondition");
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
    public T shouldHave(Condition... conditions) {
        SpecificConditionContext.with(this, () ->
                getDelegate().shouldHave(conditions)
        );
        return (T) this;
    }

    @Override
    public T shouldBe(Condition... conditions) {
        SpecificConditionContext.with(this, () ->
                getDelegate().shouldBe(conditions)
        );
        return (T) this;
    }

    @Override
    public T shouldNotHave(Condition... conditions) {
        SpecificConditionContext.with(this, () ->
                getDelegate().shouldNotHave(conditions)
        );
        return (T) this;
    }

    @Override
    public T shouldNotBe(Condition... conditions) {
        SpecificConditionContext.with(this, () ->
                getDelegate().shouldNotBe(conditions)
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