package com.haulmont.masquerade.components;

import java.time.LocalDateTime;

public interface DateTimeField extends Field<DateTimeField> {
    LocalDateTime getConvertedValue();

    String getValue();
    void setValue(String value);
}