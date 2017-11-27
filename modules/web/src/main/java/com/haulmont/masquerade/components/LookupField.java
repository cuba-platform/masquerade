package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;

import java.util.List;

/**
 * LookupField component.
 * <br>
 * Supported conditions:
 * <ul>
 *     <li>{@link Conditions#visible}</li>
 *     <li>{@link Conditions#hidden}</li>
 *     <li>{@link Conditions#value(String)}</li>
 * </ul>
 */
public interface LookupField extends Field<LookupField> {
    String getValue();
    LookupField setValue(String value);

    LookupField setFilter(String filter);

    OptionsPopup openOptionsPopup();
    OptionsPopup getOptionsPopup();
    LookupField closeOptionsPopup();

    /**
     * todo {@code Conditions.visibleOptions(List<String>)}
     */
    interface OptionsPopup extends SelenideElementWrapper<OptionsPopup>, ByLocator {
        List<String> getVisibleOptions();

        LookupField select(String option);

        OptionsPopup nextPage();
        boolean hasNextPage();
        OptionsPopup previousPage();
    }
}