package com.haulmont.masquerade.conditions;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class Options extends SpecificCondition {
    private List<String> options;

    public Options(List<String> options) {
        super("options");

        this.options = ImmutableList.copyOf(options);
    }

    public List<String> getOptions() {
        return options;
    }
}