package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.BoxLayout;
import com.haulmont.masquerade.util.NotImplementedException;
import org.openqa.selenium.By;

public class BoxLayoutImpl extends AbstractComponent<BoxLayout> implements BoxLayout {
    public BoxLayoutImpl(By by) {
        super(by);
    }
}