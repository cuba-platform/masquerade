package com.haulmont.masquerade.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.impl.Html;
import org.openqa.selenium.WebElement;

public class Value extends Condition {

    private String expectedValue;

    public Value(String expectedValue) {
        super("value");
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean apply(WebElement element) {
        return Html.text.contains(getAttributeValue(element, "value"), expectedValue);
    }

    @Override
    public String toString() {
        return name + " '" + expectedValue + "'";
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    protected String getAttributeValue(WebElement element, String attributeName) {
        String attr = element.getAttribute(attributeName);
        return attr == null ? "" : attr;
    }
}