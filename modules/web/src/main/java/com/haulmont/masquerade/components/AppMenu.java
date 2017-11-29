package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

import java.util.Arrays;

public interface AppMenu extends Component<AppMenu> {
    @Log
    <T> T openItem(Class<T> clazz, String... path);
    @Log
    <T> T openItem(Menu<T> menu);
    @Log
    void openItem(String... path);

    class Menu<T> {
        private final Class<T> screenClass;
        private final String[] path;

        public Menu(Class<T> screenClass, String... path) {
            this.screenClass = screenClass;
            this.path = path;
        }

        public Class<T> getScreenClass() {
            return screenClass;
        }

        public String[] getPath() {
            return path;
        }

        @Override
        public String toString() {
            return "Menu{" +
                    "screenClass=" + screenClass +
                    ", path=" + Arrays.toString(path) +
                    '}';
        }
    }
}