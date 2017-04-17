package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;

/**
 * TextField component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#visible}</li>
 *     <li>{@link Conditions#hidden}</li>
 *     <li>{@link Conditions#enabled}</li>
 *     <li>{@link Conditions#disabled}</li>
 *     <li>{@link Conditions#editable}</li>
 *     <li>{@link Conditions#readonly}</li>
 * </ul>
 */
public interface TextField extends TextInput<TextField> {
}