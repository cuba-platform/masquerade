package com.haulmont.masquerade.components;

import com.haulmont.masquerade.base.SelenideElementWrapper;

public interface Component<T extends Component> extends SelenideElementWrapper<T> {
    boolean isEnabled();
    boolean isVisible();
}