package com.haulmont.masquerade.conditions;

public class TimeValue extends SpecificCondition {
    private String expectedValue;

    public TimeValue(String expectedValue) {
        super("timeValue");
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