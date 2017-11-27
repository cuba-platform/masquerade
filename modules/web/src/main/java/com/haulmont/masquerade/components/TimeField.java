package com.haulmont.masquerade.components;

public interface TimeField extends Field<TimeField> {
    String getValue();
    TimeField setValue(String value);
}