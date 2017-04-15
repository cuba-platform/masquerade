package com.haulmont.masquerade.components;

import com.haulmont.masquerade.base.SelenideElementWrapper;

public interface AppMenu extends Component<AppMenu> {
    SubMenu openSubMenu(String id);
    <T> T openItem(String id, Class<T> clazz);

    interface SubMenu extends SelenideElementWrapper<SubMenu> {
        SubMenu openSubMenu(String id);
        <T> T openItem(String id, Class<T> clazz);
    }
}