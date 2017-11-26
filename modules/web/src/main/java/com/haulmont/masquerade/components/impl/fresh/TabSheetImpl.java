package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.TabSheet;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import com.haulmont.masquerade.util.NotImplementedException;
import org.openqa.selenium.By;

public class TabSheetImpl extends AbstractComponent<TabSheet> implements TabSheet {
    public TabSheetImpl(By by) {
        super(by);
    }

    @Override
    public void switchTo(String tabId) {
        throw new NotImplementedException();
    }
}