package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;

/**
 * Button component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#visible}</li>
 *     <li>{@link Conditions#hidden}</li>
 *     <li>{@link Conditions#enabled}</li>
 *     <li>{@link Conditions#disabled}</li>
 *     <li>{@link Conditions.Caption}</li>
 * </ul>
 */
public interface Button extends Component<Button> {
    String getCaption();

    Button click();
}