package com.haulmont.masquerade.components;

import com.haulmont.masquerade.base.SelenideElementWrapper;

public interface Notification extends SelenideElementWrapper<Notification> {
    enum Type {
        INFO,
        WARNING,
        ERROR,
        TRAY
    }

    Type getType();
    String getText();
}