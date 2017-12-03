package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.DialogWindow;
import com.haulmont.masquerade.util.NotImplementedException;
import org.openqa.selenium.By;

public class DialogWindowImpl extends AbstractComponent<DialogWindow> implements DialogWindow {
    public DialogWindowImpl(By by) {
        super(by);
    }

    @Override
    public String getCaption() {
        throw new NotImplementedException();
    }

    @Override
    public void close() {
        throw new NotImplementedException();
    }
}