package com.haulmont.masquerade.components;

import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;
import com.haulmont.masquerade.util.Log;

import java.util.List;

public interface HasOptionsPopup<T> {
    OptionsPopup<T> getOptionsPopup();
    @Log
    OptionsPopup<T> openOptionsPopup();
    @Log
    T closeOptionsPopup();

    /**
     * Options popup.
     * <br>
     * Supported conditions:
     * <ul>
     *     <li>{@link Conditions#VISIBLE}</li>
     *     <li>{@link Conditions#HIDDEN}</li>
     *     <li>{@link Conditions#visibleOptions(String...)}</li>
     *     <li>{@link Conditions#visibleOptionsCount(int)}</li>
     * </ul>
     */
    interface OptionsPopup<T> extends SelenideElementWrapper<OptionsPopup>, ByLocator, Element {
        List<String> getVisibleOptions();

        @Log
        T select(String option);

        @Log
        OptionsPopup<T> nextPage();
        @Log
        OptionsPopup<T> previousPage();

        boolean hasNextPage();
    }
}