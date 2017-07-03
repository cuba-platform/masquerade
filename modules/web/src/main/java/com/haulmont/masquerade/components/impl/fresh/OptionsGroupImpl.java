package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.OptionsGroup;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

import java.util.List;

public class OptionsGroupImpl extends AbstractComponent<OptionsGroup> implements OptionsGroup {
    public OptionsGroupImpl(By by) {
        super(by);
    }

    @Override
    public String getSelectedValue() {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public int getSelectedIndex() {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public OptionsGroup select(String option) {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public List<String> getOptions() {
        throw new NotImplementedException("This UI component still in development");
    }
}