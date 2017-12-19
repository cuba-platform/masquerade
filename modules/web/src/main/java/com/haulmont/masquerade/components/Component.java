package com.haulmont.masquerade.components;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;

import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
import static com.leacox.motif.Motif.match;

@SuppressWarnings("unchecked")
public interface Component<T> extends SelenideElementWrapper<T>, ByLocator {
    @Override
    default boolean is(Condition condition) {
        return componentIs(match(condition), getDelegate())
                .orElse(c -> getDelegate().is(condition))
                .getMatch();
    }

    @Override
    default boolean has(Condition condition) {
        return componentHas(match(condition), getDelegate())
                .orElse(c -> getDelegate().has(condition))
                .getMatch();
    }

    @Override
    default T should(Condition... conditions) {
        matchAll(conditions, m -> componentShould(m, getDelegate())
                .orElse(c -> getDelegate().should(c)));
        return (T) this;
    }

    @Override
    default T shouldNot(Condition... conditions) {
        matchAll(conditions, m -> componentShouldNot(m, getDelegate())
                .orElse(c -> getDelegate().shouldNot(c)));

        return (T) this;
    }
}