package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.GroupBox;
import com.haulmont.masquerade.util.NotImplementedException;
import org.openqa.selenium.By;

public class GroupBoxImpl extends AbstractComponent<GroupBox> implements GroupBox {
    public GroupBoxImpl(By by) {
        super(by);
    }

    @Override
    public void collapse() {
        throw new NotImplementedException();
    }

    @Override
    public void expand() {
        throw new NotImplementedException();
    }

    @Override
    public String getCaption() {
        throw new NotImplementedException();
    }
}