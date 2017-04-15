package com.haulmont.masquerade.components;

import java.time.LocalDate;

public interface DateField extends Field<DateField> {
    LocalDate getConvertedValue();

    String getValue();
    void setValue(String value);
}