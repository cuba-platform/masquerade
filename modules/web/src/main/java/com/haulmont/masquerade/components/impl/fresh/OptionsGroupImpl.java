package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.OptionsGroup;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.editable;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;
import static com.haulmont.masquerade.sys.TagNames.SPAN;

public class OptionsGroupImpl extends AbstractComponent<OptionsGroup> implements OptionsGroup {
    public OptionsGroupImpl(By by) {
        super(by);
    }

    @Override
    public String getSelectedValue() {
        return $(byChain(by, byXpath(".//input[@checked]/following-sibling::label")))
                .getText();
    }


    @Override
    public int getSelectedIndex() {
        throw new NotImplementedException("This UI component still in development");
    }

    @Override
    public OptionsGroup select(String option) {
        OptionsGroup optionsGroup = new OptionsGroupImpl(byChain(by, SPAN));
        $(byXpath(".//div[@cuba-id='type']//label[text()='" + option + "']")).click();
        return optionsGroup;
    }

    @Override
    public List<String> getOptions() {
        throw new NotImplementedException("This UI component still in development");
    }
}