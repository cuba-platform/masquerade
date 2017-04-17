package com.haulmont.masquerade.components;

import org.openqa.selenium.By;

public interface Container<T extends Container> extends Component<T> {
    <C> C child(Class<C> childClazz);
    <C> C child(Class<C> clazz, String... childPath);
    <C> C child(Class<C> childClazz, By childBy);
}