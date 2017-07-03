package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.TabSheet;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

public class TabSheetImpl extends AbstractComponent<TabSheet> implements TabSheet {
    public TabSheetImpl(By by) {
        super(by);
    }

    @Override
    public void switchTo(String tabId) {
        throw new NotImplementedException("This UI component still in development");
    }
}