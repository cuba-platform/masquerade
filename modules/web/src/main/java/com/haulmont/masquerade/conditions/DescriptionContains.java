package com.haulmont.masquerade.conditions;

public class DescriptionContains extends SpecificCondition {
    private final String descriptionSubstring;

    public DescriptionContains(String descriptionSubstring) {
        super("descriptionContains");
        this.descriptionSubstring = descriptionSubstring;
    }

    public String getDescriptionSubstring() {
        return descriptionSubstring;
    }
}