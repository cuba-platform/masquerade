package com.haulmont.masquerade.components;

public interface AppMenu extends Component<AppMenu> {
    <T> T openItem(Class<T> clazz, String... path);

    <T> T openItem(Menu<T> menu);

    void openItem(String... path);

    class Menu<T> {
        private Class<T> screenClass;
        private String[] path;

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
    }
}