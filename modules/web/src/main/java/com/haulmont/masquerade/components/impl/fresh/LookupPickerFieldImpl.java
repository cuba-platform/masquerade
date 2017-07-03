package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.LookupPickerField;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

public class LookupPickerFieldImpl extends AbstractComponent<LookupPickerField> implements LookupPickerField {
    public LookupPickerFieldImpl(By by) {
        super(by);
    }
}