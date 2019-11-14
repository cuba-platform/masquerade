package com.haulmont.masquerade.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selectors.byCssSelector;

/**
 * Condition is intended to check whether checkbox input is checked or not.
 */
public class CheckBoxChecked extends Condition {

    public static final String INPUT_TAG_NAME = "input";

    public CheckBoxChecked(String name) {
        super(name);
    }

    @Override
    public boolean apply(Driver driver, WebElement element) {
        WebElement input = element;

        if (!INPUT_TAG_NAME.equals(input.getTagName())) {
            input = element.findElement(byCssSelector("input[type=\"checkbox\"]"));
        }

        return input != null
                && INPUT_TAG_NAME.equals(input.getTagName())
                        ? driver.executeJavaScript("return arguments[0].checked", input)
                        : false;
    }
}
