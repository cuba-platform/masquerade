package com.haulmont.masquerade.components;

public interface GroupBox extends Container<GroupBox> {
    void collapse();
    void expand();

    String getCaption();
}