package com.haulmont.masquerade;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.Component;
import com.haulmont.masquerade.conditions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Conditions for {@link Component#should(Condition...)} methods.
 */
public class Conditions {
    protected Conditions() {
    }

    public static final Condition ENABLED = new SpecificCondition("enabled");

    public static final Condition DISABLED = new SpecificCondition("disabled");

    public static final Condition VISIBLE = Condition.visible;

    public static final Condition HIDDEN = Condition.hidden;

    public static final Condition EXIST = Condition.exist;

    public static final Condition APPEAR = VISIBLE;

    public static final Condition APPEARS = VISIBLE;

    public static final Condition DISAPPEARS = HIDDEN;

    public static final Condition DISAPPEAR = HIDDEN;

    public static final Condition READONLY = new SpecificCondition("readonly");

    public static final Condition EDITABLE = new SpecificCondition("editable");

    public static final Condition REQUIRED = new SpecificCondition("required");

    public static final Condition CHECKED = new SpecificCondition("checked");

    public static final Condition SELECTED = new SpecificCondition("selected");

    public static final Condition EXPANDED = new SpecificCondition("expanded");

    public static final Condition COLLAPSED = new SpecificCondition("collapsed");

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