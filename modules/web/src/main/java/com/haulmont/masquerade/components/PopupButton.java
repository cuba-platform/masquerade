package com.haulmont.masquerade.components;

import com.haulmont.masquerade.util.Log;
import com.haulmont.masquerade.base.ByLocator;
import com.haulmont.masquerade.base.SelenideElementWrapper;

import java.util.List;

public interface PopupButton extends Component<PopupButton> {
    void click(String option);

    @Log
    PopupContent openPopupContent();
    PopupContent getPopupContent();

    interface PopupContent extends SelenideElementWrapper<PopupContent>, ByLocator {
        void select(String option);

        List<String> getOptions();
    }
}