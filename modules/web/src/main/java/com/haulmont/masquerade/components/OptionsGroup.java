package com.haulmont.masquerade.components;

import java.util.List;

public interface OptionsGroup extends Field<OptionsGroup> {
    String getSelectedValue();
    int getSelectedIndex();
    OptionsGroup select(String option);

    List<String> getOptions();
}