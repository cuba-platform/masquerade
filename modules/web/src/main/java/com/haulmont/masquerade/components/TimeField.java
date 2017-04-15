package com.haulmont.masquerade.components;

import java.time.LocalTime;

public interface TimeField extends Field<TimeField> {
    LocalTime getConvertedValue();

    String getValue();
    void setValue(String value);
}