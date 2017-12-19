package com.haulmont.masquerade.conditions;

public class ValueContains extends SpecificCondition {

    private String expectedValueSubstring;

    public ValueContains(String expectedValueSubstring) {
        super("valueContains");
        this.expectedValueSubstring = expectedValueSubstring;
    }

    @Override
    public String toString() {
        return name + " '" + expectedValueSubstring + "'";
    }

    public String getExpectedValueSubstring() {
        return expectedValueSubstring;
    }
}