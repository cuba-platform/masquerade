package com.haulmont.masquerade.conditions;

public class Description extends SpecificCondition {
    private final String description;

    public Description(String description) {
        super("description");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}