package com.haulmont.masquerade.sys.matchers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.conditions.Options;
import com.haulmont.masquerade.conditions.OptionsCount;
import com.haulmont.masquerade.conditions.Value;
import com.haulmont.masquerade.conditions.ValueContains;
import com.leacox.motif.MatchesExact;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.matching.FluentMatching;
import com.leacox.motif.matching.FluentMatchingC;
import com.leacox.motif.matching.FluentMatchingR;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.readonly;
import static com.google.common.base.Strings.nullToEmpty;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.sys.VaadinClassNames.*;
import static com.leacox.motif.MatchesExact.eq;

public final class ConditionCases {
    private ConditionCases() {
    }

    public static DecomposableMatchBuilder1<Condition, Value> isValue() {
        return InstanceOfCases.hasType(Value.class);
    }

    public static DecomposableMatchBuilder1<Condition, ValueContains> isValueContains() {
        return InstanceOfCases.hasType(ValueContains.class);
    }

    public static DecomposableMatchBuilder1<Condition, Options> isOptions() {
        return InstanceOfCases.hasType(Options.class);
    }

    public static DecomposableMatchBuilder1<Condition, OptionsCount> isOptionsCount() {
        return InstanceOfCases.hasType(OptionsCount.class);
    }

    public static MatchesExact<Condition> isRequired() {
        return eq(required);
    }

    public static MatchesExact<Condition> isDisabled() {
        return eq(disabled);
    }

    public static MatchesExact<Condition> isEnabled() {
        return eq(enabled);
    }

    public static MatchesExact<Condition> isEditable() {
        return eq(editable);
    }

    public static MatchesExact<Condition> isReadonly() {
        return eq(readonly);
    }

    @SuppressWarnings("CodeBlock2Expr")
    public static FluentMatchingC<Condition> fieldShould(FluentMatching<Condition> matching,
                                                         SelenideElement inputImpl) {
        return matching
                .when(isValue()).then(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    inputImpl
                            .shouldBe(visible)
                            .shouldHave(exactValue(expectedValue));
                })
                .when(isValueContains()).then(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValueSubstring());
                    inputImpl
                            .shouldBe(visible)
                            .shouldHave(valueContains(expectedValue));
                })
                .when(isEnabled()).then(() -> {
                    inputImpl.shouldNotHave(disabledClass);
                })
                .when(isDisabled()).then(() -> {
                    inputImpl.shouldHave(disabledClass);
                })
                .when(isRequired()).then(() -> {
                    inputImpl.shouldHave(requiredClass);
                })
                .when(isReadonly()).then(() -> {
                    inputImpl.shouldHave(readonlyClass);
                })
                .when(isEditable()).then(() -> {
                    inputImpl.shouldNotHave(readonlyClass);
                });
    }

    @SuppressWarnings("CodeBlock2Expr")
    public static FluentMatchingC<Condition> fieldShouldNot(FluentMatching<Condition> matching,
                                                            SelenideElement inputImpl) {
        return matching
                .when(isValue()).then(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    inputImpl
                            .shouldBe(visible)
                            .shouldNotHave(exactValue(expectedValue));
                })
                .when(isValueContains()).then(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValueSubstring());
                    inputImpl
                            .shouldBe(visible)
                            .shouldNotHave(valueContains(expectedValue));
                })
                .when(isEnabled()).then(() -> {
                    inputImpl.shouldHave(disabledClass);
                })
                .when(isDisabled()).then(() -> {
                    inputImpl.shouldNotHave(disabledClass);
                })
                .when(isRequired()).then(() -> {
                    inputImpl.shouldNotHave(requiredClass);
                })
                .when(isReadonly()).then(() -> {
                    inputImpl.shouldNotHave(readonlyClass);
                })
                .when(isEditable()).then(() -> {
                    inputImpl.shouldHave(readonlyClass);
                });
    }

    public static FluentMatchingR<Condition, Boolean> fieldIs(FluentMatching<Condition> matching,
                                                              SelenideElement inputImpl) {
        return matching
                .when(isEnabled()).get(() ->
                        !inputImpl.has(disabledClass)
                )
                .when(isDisabled()).get(() ->
                        inputImpl.has(disabledClass)
                )
                .when(isRequired()).get(() ->
                        inputImpl.has(requiredClass)
                )
                .when(isReadonly()).get(() ->
                        inputImpl.has(readonlyClass)
                )
                .when(isEditable()).get(() ->
                        !inputImpl.has(readonlyClass)
                );
    }

    public static FluentMatchingR<Condition, Boolean> fieldHas(FluentMatching<Condition> matching,
                                                               SelenideElement inputImpl) {
        return matching
                .when(isValue()).get(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValue());
                    return inputImpl
                            .has(exactValue(expectedValue));
                })
                .when(isValueContains()).get(v -> {
                    String expectedValue = nullToEmpty(v.getExpectedValueSubstring());
                    return inputImpl
                            .has(valueContains(expectedValue));
                });
    }
}