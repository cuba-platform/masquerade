package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

public interface TextInput<T extends TextInput> extends Field<T> {
    @Log
    T setValue(String value);
    String getValue();
}