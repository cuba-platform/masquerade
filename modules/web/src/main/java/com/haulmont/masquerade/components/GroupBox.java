package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;

/**
 * GroupBox component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#visible}</li>
 *     <li>{@link Conditions#hidden}</li>
 *     <li>{@link Conditions#enabled}</li>
 *     <li>{@link Conditions#disabled}</li>
 *     <li>{@link Conditions#expanded}</li>
 *     <li>{@link Conditions#collapsed}</li>
 *     <li>{@link Conditions#caption(String)}</li>
 * </ul>
 */
public interface GroupBox extends Container<GroupBox> {
    void collapse();
    void expand();

    String getCaption();
}