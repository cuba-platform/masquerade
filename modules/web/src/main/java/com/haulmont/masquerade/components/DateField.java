package com.haulmont.masquerade.components;

public interface DateField extends Field<DateField> {
    String getValue();
    void setValue(String value);
}