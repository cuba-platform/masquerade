package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;

/**
 * GroupBox component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#VISIBLE}</li>
 *     <li>{@link Conditions#HIDDEN}</li>
 *     <li>{@link Conditions#ENABLED}</li>
 *     <li>{@link Conditions#DISABLED}</li>
 *     <li>{@link Conditions#EXPANDED}</li>
 *     <li>{@link Conditions#COLLAPSED}</li>
 *     <li>{@link Conditions#caption(String)}</li>
 * </ul>
 */
public interface GroupBox extends Container<GroupBox> {
    void collapse();
    void expand();

    String getCaption();
}