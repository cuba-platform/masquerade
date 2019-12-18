package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;
import org.openqa.selenium.By;

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
     * Expands first group row matching the given {@code groupRowSelector}.
     * <p>
     * Example:
     * <pre>
     *    $c(GroupTable, 'tableId')
     *        .expand(withText('groupRowTextSubstring'))
     * </pre>
     *
     * @param groupRowSelector selector of the group row to expand
     *
     * @return component instance
     */
    GroupTable expand(By groupRowSelector);

    /**
     * Expands first group row matching the given {@code groupRowSelector}.
     * <p>
     * Example:
     * <pre>
     *    $c(GroupTable, 'tableId')
     *        .collapse(withText('groupRowTextSubstring'))
     * </pre>
     *
     * @param groupRowSelector selector of the group row to collapse
     *
     * @return component instance
     */
    GroupTable collapse(By groupRowSelector);

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
