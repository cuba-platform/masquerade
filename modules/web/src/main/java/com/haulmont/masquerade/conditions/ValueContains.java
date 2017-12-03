package com.haulmont.masquerade.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.impl.Html;
import org.openqa.selenium.WebElement;

public class ValueContains extends Condition {

    private String expectedValueSubstring;

    public ValueContains(String expectedValueSubstring) {
        super("valueContains");
        this.expectedValueSubstring = expectedValueSubstring;
    }

    @Override
    public boolean apply(WebElement element) {
        return Html.text.contains(getAttributeValue(element, "value"), expectedValueSubstring);
    }

    @Override
    public String toString() {
        return name + " '" + expectedValueSubstring + "'";
    }

    public String getExpectedValueSubstring() {
        return expectedValueSubstring;
    }

    protected String getAttributeValue(WebElement element, String attributeName) {
        String attr = element.getAttribute(attributeName);
        return attr == null ? "" : attr;
    }
}