package com.haulmont.masquerade.conditions;

public class CaptionContains extends SpecificCondition {
    private final String captionSubstring;

    public CaptionContains(String captionSubstring) {
        super("captionContains");
        this.captionSubstring = captionSubstring;
    }

    public String getCaptionSubstring() {
        return captionSubstring;
    }
}