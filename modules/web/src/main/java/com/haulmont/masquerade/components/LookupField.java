package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.util.Log;
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
    @Log
    LookupField setValue(String value);
    String getValue();

    @Log
    LookupField setFilter(String filter);

    OptionsPopup getOptionsPopup();
    @Log
    OptionsPopup openOptionsPopup();
    @Log
    LookupField closeOptionsPopup();

    /**
     * todo {@code Conditions.visibleOptions(List<String>)}
     */
    interface OptionsPopup extends SelenideElementWrapper<OptionsPopup>, ByLocator {
        List<String> getVisibleOptions();

        @Log
        LookupField select(String option);

        @Log
        OptionsPopup nextPage();
        @Log
        OptionsPopup previousPage();

        boolean hasNextPage();
    }
}