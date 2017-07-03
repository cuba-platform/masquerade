package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.FieldGroup;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

public class FieldGroupImpl extends AbstractComponent<FieldGroup> implements FieldGroup {
    public FieldGroupImpl(By by) {
        super(by);
    }

    @Override
    public <C> C child(Class<C> clazz, String... childPath) {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public <C> C child(Class<C> childClazz, By childBy) {
        throw new NotImplementedException("This UI component still in development");
    }
}