package com.haulmont.masquerade.sys.matchers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.conditions.Value;
import com.haulmont.masquerade.conditions.ValueContains;
import com.leacox.motif.matching.FluentMatching;
import com.leacox.motif.matching.FluentMatchingR;

import static com.google.common.base.Strings.nullToEmpty;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.sys.VaadinClassNames.*;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.MatchesExact.eq;

public final class ConditionCases {
    private ConditionCases() {
    }

    public static FluentMatchingR<Condition, Boolean> componentApply(FluentMatching<Condition> matching,
                                                                     SelenideElement impl) {
        return matching
                .when(eq(enabled)).get(() ->
                        !impl.has(disabledClass)
                )
                .when(eq(disabled)).get(() ->
                        impl.has(disabledClass)
                );
    }

    @SuppressWarnings("CodeBlock2Expr")
    public static FluentMatchingR<Condition, Boolean> fieldApply(FluentMatching<Condition> matching,
                                                                 SelenideElement impl,
                                                                 SelenideElement inputImpl) {
        return matching
                .when(hasType(Value.class)).get(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    return inputImpl.is(Condition.visible) && inputImpl.has(Condition.exactValue(expectedValue));
                })
                .when(hasType(ValueContains.class)).get(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValueSubstring());
                    return inputImpl.is(Condition.visible) && inputImpl.has(Condition.value(expectedValue));
                })
                .when(eq(enabled)).get(() -> {
                    return !impl.has(disabledClass);
                })
                .when(eq(disabled)).get(() -> {
                    return impl.has(disabledClass);
                })
                .when(eq(required)).get(() -> {
                    return impl.has(requiredClass);
                })
                .when(eq(readonly)).get(() -> {
                    return !impl.has(readonlyClass);
                })
                .when(eq(editable)).get(() -> {
                    return !impl.has(readonlyClass);
                });
    }
}