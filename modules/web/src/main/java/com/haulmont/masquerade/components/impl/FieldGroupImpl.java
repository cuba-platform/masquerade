package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.FieldGroup;
import org.openqa.selenium.By;

public class FieldGroupImpl extends AbstractComponent<FieldGroup> implements FieldGroup {
    public FieldGroupImpl(By by) {
        super(by);
    }
}