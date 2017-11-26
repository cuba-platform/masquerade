package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.Selectors;
import com.haulmont.masquerade.components.OptionsGroup;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import com.haulmont.masquerade.util.NotImplementedException;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.SPAN;

public class OptionsGroupImpl extends AbstractComponent<OptionsGroup> implements OptionsGroup {
    public OptionsGroupImpl(By by) {
        super(by);
    }

    @Override
    public String getSelectedValue() {
        impl.shouldBe(visible);

        return $(byChain(by, byXpath(".//input[@checked]/following-sibling::label")))
                .getText();
    }

    @Override
    public int getSelectedIndex() {
        throw new NotImplementedException();
    }

    @Override
    public OptionsGroup select(String option) {
        $(byChain(by, SPAN, Selectors.byText(option)))
                .shouldBe(visible)
                .click();
        return this;
    }

    @Override
    public List<String> getOptions() {
        throw new NotImplementedException();
    }
}