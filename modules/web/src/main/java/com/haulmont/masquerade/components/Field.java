package com.haulmont.masquerade.components;

public interface Field<T extends Field> extends Component<T> {
    /**
     * @deprecated Will be removed after refactor. Use {@code #is(Conditions.editable)}
     */
    @Deprecated
    boolean isEditable();
}