package com.haulmont.masquerade.sys.matchers;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.conditions.Options;
import com.haulmont.masquerade.conditions.OptionsCount;
import com.haulmont.masquerade.conditions.Value;
import com.leacox.motif.MatchesExact;
import com.leacox.motif.extract.DecomposableMatchBuilder1;

import static com.codeborne.selenide.Condition.readonly;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Conditions.required;
import static com.leacox.motif.MatchesExact.eq;

public final class ConditionCases {
    private ConditionCases() {
    }

    public static DecomposableMatchBuilder1<Condition, Value> isValue() {
        return InstanceOfCases.hasType(Value.class);
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

    public static MatchesExact<Condition> isEditable() {
        return eq(editable);
    }

    public static MatchesExact<Condition> isReadonly() {
        return eq(readonly);
    }
}