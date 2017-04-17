package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;

/**
 * CheckBox component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#visible}</li>
 *     <li>{@link Conditions#hidden}</li>
 *     <li>{@link Conditions#enabled}</li>
 *     <li>{@link Conditions#disabled}</li>
 *     <li>{@link Conditions#readonly}</li>
 *     <li>{@link Conditions#editable}</li>
 *     <li>{@link Conditions#checked}</li>
 *     <li>{@link Conditions#selected}</li>
 *     <li>{@link Conditions.Caption}</li>
 * </ul>
 */
public interface CheckBox extends Field<CheckBox> {
    void setChecked(boolean checked);
    boolean isChecked();

    String getCaption();
}