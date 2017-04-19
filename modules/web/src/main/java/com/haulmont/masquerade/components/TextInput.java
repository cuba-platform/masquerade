package com.haulmont.masquerade.components;

public interface TextInput<T extends TextInput> extends Field<T> {
    T setValue(String value);
    String getValue();
}