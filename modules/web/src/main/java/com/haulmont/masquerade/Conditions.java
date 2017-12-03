package com.haulmont.masquerade;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.conditions.*;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class Conditions {
    protected Conditions() {
    }

    public static final Condition enabled = Condition.enabled;

    public static final Condition disabled = Condition.disabled;

    public static final Condition visible = Condition.visible;

    public static final Condition hidden = Condition.hidden;

    public static final Condition exists = Condition.exist;

    public static final Condition appear = visible;

    public static final Condition appears = visible;

    public static final Condition disappears = hidden;

    public static final Condition disappear = hidden;

    public static final Condition readonly = Condition.readonly;

    public static final Condition editable = new Condition("editable") {
        @Override
        public boolean apply(WebElement element) {
            return element.getAttribute("readonly") == null;
        }
    };

    public static final Condition required = new SpecificCondition("required");

    public static final Condition checked = Condition.checked;

    public static final Condition selected = Condition.selected;

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
}