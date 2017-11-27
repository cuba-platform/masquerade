package com.haulmont.masquerade.components;

public interface DateTimeField extends Field<DateTimeField> {
    String getDateValue();
    DateTimeField setDateValue(String value);

    String getTimeValue();
    DateTimeField setTimeValue(String value);
}