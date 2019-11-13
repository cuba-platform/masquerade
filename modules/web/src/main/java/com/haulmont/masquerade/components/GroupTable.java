package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;

/**
 * GroupTable component.
 * <br>
 * Supported conditions:
 * <ul>
 * <li>{@link Conditions#EXPANDED}</li>
 * <li>{@link Conditions#COLLAPSED}</li>
 * </ul>
 */
public interface GroupTable extends Component<GroupTable> {

    /**
     * Expands all groups.
     *
     * @return component instance
     */
    @Log
    GroupTable expandAll();

    /**
     * Collapses all groups.
     *
     * @return component instance
     */
    @Log
    GroupTable collapseAll();

    /**
     * @return current component as {@link Table} instance
     */
    @Log
    Table asTable();
}
