package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;

/**
 * CheckBox component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#VISIBLE}</li>
 *     <li>{@link Conditions#HIDDEN}</li>
 *     <li>{@link Conditions#ENABLED}</li>
 *     <li>{@link Conditions#DISABLED}</li>
 *     <li>{@link Conditions#READONLY}</li>
 *     <li>{@link Conditions#EDITABLE}</li>
 *     <li>{@link Conditions#CHECKED}</li>
 *     <li>{@link Conditions#SELECTED}</li>
 *     <li>{@link Conditions#caption(String)}</li>
 * </ul>
 */
public interface CheckBox extends Field<CheckBox> {
    @Log
    CheckBox setChecked(boolean checked);
    boolean isChecked();

    String getCaption();
}