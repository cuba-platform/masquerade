package com.haulmont.masquerade.components;

public interface TimeField extends Field<TimeField> {
    String getValue();
    void setValue(String value);
}