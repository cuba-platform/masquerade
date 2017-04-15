package com.haulmont.masquerade.components;

public interface LookupField extends Field<LookupField> {
    String getValue();
    void setValue(String value);
}