package com.haulmont.masquerade.components;

import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;

import java.util.List;

public interface LookupField extends Field<LookupField> {
    String getValue();
    LookupField setValue(String value);

    LookupField setFilter(String filter);

    OptionsPopup openOptionsPopup();
    OptionsPopup getOptionsPopup();
    LookupField closeOptionsPopup();

    interface OptionsPopup extends SelenideElementWrapper<OptionsPopup>, ByLocator {
        List<String> getVisibleOptions();

        LookupField select(String option);

        OptionsPopup nextPage();
        OptionsPopup previousPage();
    }
}