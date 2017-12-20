package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;

public interface LookupPickerField extends Field<LookupPickerField>, HasActions, HasOptionsPopup<LookupPickerField> {
    @Log
    LookupPickerField setValue(String value);
    String getValue();

    @Log
    LookupPickerField setFilter(String filter);
}