package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

public interface TabSheet extends Component<TabSheet> {
    @Log
    void switchTo(String tabId);
}