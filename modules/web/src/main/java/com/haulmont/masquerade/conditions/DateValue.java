package com.haulmont.masquerade.conditions;

public class DateValue extends SpecificCondition {
    private String expectedValue;

    public DateValue(String expectedValue) {
        super("dateValue");
        this.expectedValue = expectedValue;
    }

    @Override
    public String toString() {
        return name + " '" + expectedValue + "'";
    }

    public String getExpectedValue() {
        return expectedValue;
    }
}