package com.haulmont.masquerade.conditions;

import com.haulmont.masquerade.components.Notification;

public class NotificationType extends SpecificCondition {
    private Notification.Type type;

    public NotificationType(Notification.Type type) {
        super("type");
        this.type = type;
    }

    public Notification.Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " '" + type + "'";
    }
}