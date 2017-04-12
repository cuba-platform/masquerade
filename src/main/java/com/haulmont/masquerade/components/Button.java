package com.haulmont.masquerade.components;

public interface Button {
    String getCaption();

    boolean isEnabled();

    void click();
}