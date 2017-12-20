package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;

/**
 * DateTimeField component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#visible}</li>
 *     <li>{@link Conditions#hidden}</li>
 *     <li>{@link Conditions#enabled}</li>
 *     <li>{@link Conditions#disabled}</li>
 *     <li>{@link Conditions#required}</li>
 *     <li>{@link Conditions#readonly}</li>
 *     <li>{@link Conditions#editable}</li>
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