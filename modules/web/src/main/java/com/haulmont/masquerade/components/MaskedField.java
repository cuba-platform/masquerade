package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;

/**
 * TextField component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#VISIBLE}</li>
 *     <li>{@link Conditions#HIDDEN}</li>
 *     <li>{@link Conditions#ENABLED}</li>
 *     <li>{@link Conditions#DISABLED}</li>
 *     <li>{@link Conditions#EDITABLE}</li>
 *     <li>{@link Conditions#READONLY}</li>
 *     <li>{@link Conditions#REQUIRED}</li>
 *     <li>{@link Conditions#value(String)}</li>
 *     <li>{@link Conditions#valueContains(String)}</li>
 * </ul>
 */
public interface MaskedField extends TextInput<MaskedField> {

}