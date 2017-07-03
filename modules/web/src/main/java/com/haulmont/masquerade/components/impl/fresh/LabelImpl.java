package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.Label;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

// todo Conditions.text()
// todo Conditions.value()
public class LabelImpl extends AbstractComponent<Label> implements Label {

    public LabelImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        return impl.getText();
    }
}