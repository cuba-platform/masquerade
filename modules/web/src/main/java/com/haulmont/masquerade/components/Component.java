package com.haulmont.masquerade.components;

import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;

public interface Component<T> extends SelenideElementWrapper<T>, ByLocator {
}