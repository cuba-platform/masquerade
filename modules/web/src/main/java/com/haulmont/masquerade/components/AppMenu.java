package com.haulmont.masquerade.components;

public interface AppMenu extends Component<AppMenu> {
    <T> T openItem(Class<T> clazz, String... path);

    void openItem(String... path);
}