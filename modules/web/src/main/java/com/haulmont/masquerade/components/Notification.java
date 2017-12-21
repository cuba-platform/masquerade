package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;
import com.haulmont.masquerade.util.Log;

/**
 * Notification overlay.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#VISIBLE}</li>
 *     <li>{@link Conditions#HIDDEN}</li>
 *     <li>{@link Conditions#APPEAR}</li>
 *     <li>{@link Conditions#DISAPPEAR}</li>
 *     <li>{@link Conditions#caption(String)}</li>
 *     <li>{@link Conditions#captionContains(String)}</li>
 *     <li>{@link Conditions#description(String)}</li>
 *     <li>{@link Conditions#descriptionContains(String)}</li>
 * </ul>
 */
public interface Notification extends SelenideElementWrapper<Notification>, Overlay, ByLocator {
    enum Type {
        HUMANIZED,
        WARNING,
        ERROR,
        TRAY
    }

    Type getType();
    String getCaption();
    String getDescription();

    @Log
    Notification clickToClose();
}