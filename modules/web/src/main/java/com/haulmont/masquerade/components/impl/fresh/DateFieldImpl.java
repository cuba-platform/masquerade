package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.DateField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

public class DateFieldImpl extends AbstractComponent<DateField> implements DateField {

    public DateFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getValue() {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public void setValue(String value) {
        throw new NotImplementedException("This UI component still in development");
    }
}