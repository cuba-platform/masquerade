package com.haulmont.masquerade.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

@SuppressWarnings("unchecked")
public interface SelenideElementWrapper<T> {
    SelenideElement delegate();

    default boolean exists() {
        return delegate().exists();
    }

    default boolean isVisible() {
        return is(visible);
    }

    default boolean is(Condition condition) {
        return delegate().is(condition);
    }

    default boolean has(Condition condition) {
        return delegate().has(condition);
    }

    default T should(Condition... condition) {
        delegate().should(condition);
        return (T) this;
    }

    default T shouldHave(Condition... condition) {
        return should(condition);
    }

    default T shouldBe(Condition... condition) {
        return should(condition);
    }

    default T shouldNot(Condition... condition) {
        delegate().shouldNot(condition);
        return (T) this;
    }

    default T shouldNotHave(Condition... condition) {
        return (T) shouldNot(condition);
    }

    default T shouldNotBe(Condition... condition) {
        return shouldNot(condition);
    }

    default T waitUntil(Condition condition, long timeoutMilliseconds) {
        delegate().waitUntil(condition, timeoutMilliseconds);
        return (T) this;
    }

    default T waitUntil(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        delegate().waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return (T) this;
    }

    default T waitWhile(Condition condition, long timeoutMilliseconds) {
        delegate().waitWhile(condition, timeoutMilliseconds);
        return (T) this;
    }

    default T waitWhile(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        delegate().waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return (T) this;
    }

    default String getAttribute(String name) {
        return delegate().getAttribute(name);
    }

    default String getCssValue(String propertyName) {
        return delegate().getCssValue(propertyName);
    }
}