package com.haulmont.masquerade;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Conditions extends Condition {
    public Conditions(String name) {
        super(name);
    }

    /**
     * Implementation depends on Component
     */
    public abstract static class ComponentSpecificCondition extends Condition {
        public ComponentSpecificCondition(String name) {
            super(name);
        }

        @Override
        public boolean apply(WebElement element) {
            throw new RuntimeException("ComponentSpecificCondition must be checked ony in component implementations");
        }
    }

    public static class Caption extends ComponentSpecificCondition {
        private final String caption;

        public Caption(String caption) {
            super("caption");
            this.caption = caption;
        }

        public String getCaption() {
            return caption;
        }
    }

    public static Caption caption(String caption) {
        return new Caption(caption);
    }

    public static final Condition editable = new Condition("editable") {
        @Override
        public boolean apply(WebElement element) {
            return element.getAttribute("readonly") == null;
        }
    };

    public static class Options extends ComponentSpecificCondition {
        private List<String> options;

        public Options(List<String> options) {
            super("options");

            this.options = new ArrayList<>(options);
        }

        public List<String> getOptions() {
            return Collections.unmodifiableList(options);
        }
    }

    public static Options visibleOptions(List<String> options) {
        return new Options(options);
    }

    public static Options visibleOptions(String... options) {
        return new Options(Arrays.asList(options));
    }

    public static class OptionsCount extends ComponentSpecificCondition {
        private int count;

        public OptionsCount(int count) {
            super("optionsCount");
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }

    public static OptionsCount visibleOptionsCount(int count) {
        return new OptionsCount(count);
    }
}