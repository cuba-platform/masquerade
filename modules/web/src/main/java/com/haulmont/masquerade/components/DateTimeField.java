package com.haulmont.masquerade.components;

public interface DateTimeField extends Field<DateTimeField> {
    String getDateValue();
    void setDateValue(String value);

    String getTimeValue();
    void setTimeValue(String value);
}