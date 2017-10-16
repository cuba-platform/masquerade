package com.haulmont.masquerade.components;

public interface PickerField extends Field<PickerField> {
    class Action {
        private final String id;

        public Action(String id) {
            this.id = id;
        }

    }

    Action OPEN = new Action("open");
    Action CLEAR = new Action("clear");
    Action LOOKUP = new Action("lookup");

    void triggerAction(Action action);
    <T> T triggerAction(Class<T> clazz, Action action);

    String getValue();
}