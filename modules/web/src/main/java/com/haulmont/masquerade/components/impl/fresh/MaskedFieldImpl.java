package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.MaskedField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

public class MaskedFieldImpl extends AbstractComponent<MaskedField> implements MaskedField {
    public MaskedFieldImpl(By by) {
        super(by);
    }

    @Override
    public MaskedField setValue(String value) {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public String getValue() {
        throw new NotImplementedException("This UI component still in development");
    }
}