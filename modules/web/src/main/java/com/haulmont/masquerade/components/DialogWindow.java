package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

public interface DialogWindow extends Container<DialogWindow> {
    String getCaption();

    @Log
    void close();
}