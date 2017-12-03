package com.haulmont.masquerade.conditions;

public class OptionsCount extends SpecificCondition {
    private int count;

    public OptionsCount(int count) {
        super("optionsCount");
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}