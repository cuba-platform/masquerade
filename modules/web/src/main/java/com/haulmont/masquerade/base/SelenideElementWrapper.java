package com.haulmont.masquerade.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;

@SuppressWarnings("unchecked")
public interface SelenideElementWrapper<T> {
    SelenideElement getDelegate();

    default boolean exists() {
        return getDelegate().exists();
    }

    /**
     * @deprecated Will be removed after refactor. Use {@code #is(Conditions.visible)}
     */
    @Deprecated
    default boolean isVisible() {
        return is(visible);
    }

    default boolean is(Condition condition) {
        return getDelegate().is(condition);
    }

    default boolean has(Condition condition) {
        return getDelegate().has(condition);
    }

    default T should(Condition... condition) {
        getDelegate().should(condition);
        return (T) this;
    }

    default T shouldHave(Condition... condition) {
        return should(condition);
    }

    default T shouldBe(Condition... condition) {
        return should(condition);
    }

    default T shouldNot(Condition... condition) {
        getDelegate().shouldNot(condition);
        return (T) this;
    }

    default T shouldNotHave(Condition... condition) {
        return (T) shouldNot(condition);
    }

    default T shouldNotBe(Condition... condition) {
        return shouldNot(condition);
    }

    default T waitUntil(Condition condition, long timeoutMilliseconds) {
        getDelegate().waitUntil(condition, timeoutMilliseconds);
        return (T) this;
    }

    default T waitUntil(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        getDelegate().waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return (T) this;
    }

    default T waitWhile(Condition condition, long timeoutMilliseconds) {
        getDelegate().waitWhile(condition, timeoutMilliseconds);
        return (T) this;
    }

    default T waitWhile(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        getDelegate().waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return (T) this;
    }

    default String getAttribute(String name) {
        return getDelegate().getAttribute(name);
    }

    default String getCssValue(String propertyName) {
        return getDelegate().getCssValue(propertyName);
    }
}