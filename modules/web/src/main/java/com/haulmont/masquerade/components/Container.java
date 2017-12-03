package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Components;
import org.openqa.selenium.By;

import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byPath;

public interface Container<T> extends Component<T> {
    default <C> C child(Class<C> childClazz, String... childPath) {
        return child(childClazz, byPath(childPath));
    }

    default <C> C child(Class<C> childClazz, By childBy) {
        return Components.wire(childClazz, byChain(getBy(), childBy));
    }
}