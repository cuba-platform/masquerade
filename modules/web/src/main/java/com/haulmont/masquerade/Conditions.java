package com.haulmont.masquerade;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.conditions.Caption;
import com.haulmont.masquerade.conditions.Options;
import com.haulmont.masquerade.conditions.OptionsCount;
import com.haulmont.masquerade.conditions.Value;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public abstract class Conditions extends Condition {
    public Conditions(String name) {
        super(name);
    }

    public static Caption caption(String caption) {
        return new Caption(caption);
    }

    public static Condition value(String expectedValue) {
        return new Value(expectedValue);
    }

    public static final Condition editable = new Condition("editable") {
        @Override
        public boolean apply(WebElement element) {
            return element.getAttribute("readonly") == null;
        }
    };

    public static Options visibleOptions(List<String> options) {
        return new Options(options);
    }

    public static Options visibleOptions(String... options) {
        return new Options(Arrays.asList(options));
    }

    public static OptionsCount visibleOptionsCount(int count) {
        return new OptionsCount(count);
    }
}