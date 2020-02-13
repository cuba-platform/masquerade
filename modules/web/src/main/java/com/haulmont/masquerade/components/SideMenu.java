package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;

import java.util.Arrays;

/**
 * SideMenu component.
 * <p/>
 * Supported conditions for collapsible SideMenu:
 * <ul>
 * <li>{@link Conditions#EXPANDED}</li>
 * <li>{@link Conditions#COLLAPSED}</li>
 * </ul>
 */
public interface SideMenu extends Component<SideMenu> {

    /**
     * Opens side menu item matching the given {@code screenClass} and {@code path}.
     * <p>
     * Example:
     * <pre>
     *     $c(SideMenu).openItem(UserBrowser, 'administration', 'sec$User.browse')
     * </pre>
     *
     * @param screenClass screen class to open
     * @param path        path to menu item
     * @param <T>         type of screen class
     * @return screen class wired by path
     */
    @Log
    <T> T openItem(Class<T> screenClass, String... path);

    /**
     * Opens side menu item matching the given {@link Menu}.
     * <p>
     * Example:
     * <pre>
     *     $c(SideMenu).openItem(new AppMenu.Menu<>(UserBrowser, 'administration', 'sec$User.browse'))
     * </pre>
     *
     * @param menu menu item
     * @param <T>  type of screen class
     * @return screen class wired by path
     */
    @Log
    <T> T openItem(Menu<T> menu);

    /**
     * Opens side menu item matching the given {@code path}.
     * <p>
     * Example:
     * <pre>
     *     $c(SideMenu).openItem('administration', 'sec$User.browse')
     * </pre>
     *
     * @param path path to menu item
     */
    @Log
    void openItem(String... path);

    /**
     * Expands side menu.
     *
     * @return component instance
     */
    @Log
    SideMenu expand();

    /**
     * Collapses side menu.
     *
     * @return component instance
     */
    @Log
    SideMenu collapse();

    /**
     * Menu item class.
     *
     * @param <T> type of screen class
     */
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
