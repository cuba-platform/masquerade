package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;

import java.util.List;

/**
 * OptionsGroup component with single select.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#VISIBLE}</li>
 *     <li>{@link Conditions#HIDDEN}</li>
 *     <li>{@link Conditions#ENABLED}</li>
 *     <li>{@link Conditions#DISABLED}</li>
 *     <li>{@link Conditions#value(String)}</li>
 *     <li>{@link Conditions#options(String...)}</li>
 *     <li>{@link Conditions#optionsCount(int)}</li>
 * </ul>
 */
public interface OptionsGroup extends Field<OptionsGroup> {
    String getSelectedValue();
    int getSelectedIndex();

    @Log
    OptionsGroup select(String option);

    List<String> getOptions();
}