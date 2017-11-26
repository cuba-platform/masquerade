package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.BoxLayout;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import com.haulmont.masquerade.util.NotImplementedException;
import org.openqa.selenium.By;

public class BoxLayoutImpl extends AbstractComponent<BoxLayout> implements BoxLayout {
    public BoxLayoutImpl(By by) {
        super(by);
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