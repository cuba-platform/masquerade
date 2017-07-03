package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.DateTimeField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

public class DateTimeFieldImpl extends AbstractComponent<DateTimeField> implements DateTimeField {
    public DateTimeFieldImpl(By by) {
        super(by);
    }

    @Override
    public String getDateValue() {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public void setDateValue(String value) {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public String getTimeValue() {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public void setTimeValue(String value) {
        throw new NotImplementedException("This UI component still in development");
    }
}