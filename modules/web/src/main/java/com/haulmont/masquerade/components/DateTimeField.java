package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

public interface DateTimeField extends Field<DateTimeField> {
    @Log
    DateTimeField setDateValue(String value);
    String getDateValue();

    @Log
    DateTimeField setTimeValue(String value);
    String getTimeValue();
}