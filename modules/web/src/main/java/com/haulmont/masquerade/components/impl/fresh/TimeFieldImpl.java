package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.TimeField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

import java.time.LocalTime;

public class TimeFieldImpl extends AbstractComponent<TimeField> implements TimeField {
    public TimeFieldImpl(By by) {
        super(by);
    }

    @Override
    public LocalTime getConvertedValue() {
        throw new NotImplementedException("This UI component still in development");
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