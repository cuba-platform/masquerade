package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;

/**
 * DateTimeField component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#VISIBLE}</li>
 *     <li>{@link Conditions#HIDDEN}</li>
 *     <li>{@link Conditions#ENABLED}</li>
 *     <li>{@link Conditions#DISABLED}</li>
 *     <li>{@link Conditions#REQUIRED}</li>
 *     <li>{@link Conditions#READONLY}</li>
 *     <li>{@link Conditions#EDITABLE}</li>
 *     <li>{@link Conditions#dateValue(String)}</li>
 *     <li>{@link Conditions#timeValue(String)}</li>
 * </ul>
 */
public interface DateTimeField extends Field<DateTimeField> {
    @Log
    DateTimeField setDateValue(String value);
    String getDateValue();

    @Log
    DateTimeField setTimeValue(String value);
    String getTimeValue();
}