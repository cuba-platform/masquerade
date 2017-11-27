package com.haulmont.masquerade.conditions;

public class Caption extends ComponentSpecificCondition {
    private final String caption;

    public Caption(String caption) {
        super("caption");
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}