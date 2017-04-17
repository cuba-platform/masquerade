package com.haulmont.masquerade.components;

public interface DialogWindow extends Container<DialogWindow> {
    String getCaption();

    void close();
}