package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

import java.util.List;

public interface OptionsGroup extends Field<OptionsGroup> {
    String getSelectedValue();
    int getSelectedIndex();

    @Log
    OptionsGroup select(String option);

    List<String> getOptions();
}