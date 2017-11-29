package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

public interface DateField extends Field<DateField> {
    String getDateValue();
    @Log
    DateField setDateValue(String value);
}