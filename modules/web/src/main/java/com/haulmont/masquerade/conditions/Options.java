package com.haulmont.masquerade.conditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Options extends ComponentSpecificCondition {
    private List<String> options;

    public Options(List<String> options) {
        super("options");

        this.options = new ArrayList<>(options);
    }

    public List<String> getOptions() {
        return Collections.unmodifiableList(options);
    }
}