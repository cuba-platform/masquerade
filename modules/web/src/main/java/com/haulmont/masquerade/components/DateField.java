package com.haulmont.masquerade.components;

public interface DateField extends Field<DateField> {
    String getDateValue();
    void setDateValue(String value);
}