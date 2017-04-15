package com.haulmont.masquerade;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;

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

    // todo public static class Value extends ComponentSpecificCondition
    // todo public static Value value(String value)
}