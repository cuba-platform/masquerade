package com.haulmont.masquerade;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.conditions.*;

import java.util.Arrays;
import java.util.List;

public class Conditions {
    protected Conditions() {
    }

    public static final Condition enabled = new SpecificCondition("enabled");

    public static final Condition disabled = new SpecificCondition("disabled");

    public static final Condition visible = Condition.visible;

    public static final Condition hidden = Condition.hidden;

    public static final Condition exists = Condition.exist;

    public static final Condition appear = visible;

    public static final Condition appears = visible;

    public static final Condition disappears = hidden;

    public static final Condition disappear = hidden;

    public static final Condition readonly = new SpecificCondition("readonly");

    public static final Condition editable = new SpecificCondition("editable");

    public static final Condition required = new SpecificCondition("required");

    public static final Condition checked = new SpecificCondition("checked");

    public static final Condition selected = new SpecificCondition("selected");

    public static final Condition expanded = new SpecificCondition("expanded");

    public static final Condition collapsed = new SpecificCondition("collapsed");

    public static Condition caption(String caption) {
        return new Caption(caption);
    }

    public static Condition value(String expectedValue) {
        return new Value(expectedValue);
    }

    public static Condition valueContains(String expectedValueSubstring) {
        return new ValueContains(expectedValueSubstring);
    }

    public static Condition visibleOptions(List<String> options) {
        return new Options(options);
    }

    public static Condition visibleOptions(String... options) {
        return new Options(Arrays.asList(options));
    }

    public static Condition visibleOptionsCount(int count) {
        return new OptionsCount(count);
    }

    public static Condition dateValue(String expectedValue) {
        return new DateValue(expectedValue);
    }

    public static Condition timeValue(String expectedValue) {
        return new TimeValue(expectedValue);
    }
}