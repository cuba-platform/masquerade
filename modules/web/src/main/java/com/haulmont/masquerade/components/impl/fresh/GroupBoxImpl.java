package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.GroupBox;
import com.haulmont.masquerade.components.impl.AbstractComponent;
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

    @Override
    public <C> C child(Class<C> clazz, String... childPath) {
        throw new NotImplementedException();
    }

    @Override
    public <C> C child(Class<C> childClazz, By childBy) {
        throw new NotImplementedException();
    }
}