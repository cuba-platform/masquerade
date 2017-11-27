package com.haulmont.masquerade.conditions;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;

/**
 * Implementation depends on Component
 */
public abstract class ComponentSpecificCondition extends Condition {
    public ComponentSpecificCondition(String name) {
        super(name);
    }

    @Override
    public boolean apply(WebElement element) {
        throw new RuntimeException("ComponentSpecificCondition must be checked ony in component implementations");
    }
}