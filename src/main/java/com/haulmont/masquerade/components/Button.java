package com.haulmont.masquerade.components;

public interface Button extends Component<Button> {
    String getCaption();

    void click();
}