package com.haulmont.masquerade.components;

public interface DateField extends Field<DateField> {
    String getDateValue();
    DateField setDateValue(String value);
}