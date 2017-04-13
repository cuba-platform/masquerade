package com.haulmont.masquerade.components;

public interface TextInput<T extends TextInput> extends Field<T> {
    void setValue(String value);
    String getValue();
}