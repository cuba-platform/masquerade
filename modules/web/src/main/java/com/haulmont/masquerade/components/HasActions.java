package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

public interface HasActions {
    class Action {
        private final String id;

        public Action(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    Action OPEN = new Action("open");
    Action CLEAR = new Action("clear");
    Action LOOKUP = new Action("lookup");

    @Log
    void triggerAction(Action action);
    @Log
    <T> T triggerAction(Class<T> clazz, Action action);
}