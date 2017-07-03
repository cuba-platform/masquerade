package com.haulmont.masquerade.components;

public interface TabSheet extends Component<TabSheet> {
    void switchTo(String tabId);
}