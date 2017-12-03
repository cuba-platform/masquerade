package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.LookupPickerField;
import org.openqa.selenium.By;

public class LookupPickerFieldImpl extends AbstractComponent<LookupPickerField> implements LookupPickerField {
    public LookupPickerFieldImpl(By by) {
        super(by);
    }
}