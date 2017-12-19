package com.haulmont.masquerade.conditions;

public class Value extends SpecificCondition {
    private String expectedValue;

    public Value(String expectedValue) {
        super("value");
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