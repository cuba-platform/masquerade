package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.Button;
import com.haulmont.masquerade.conditions.Caption;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.haulmont.masquerade.sys.matchers.InstanceOfCases;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.VaadinClassNames.disabledClass;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.className;

public class ButtonImpl extends AbstractComponent<Button> implements Button {

    public static final String BUTTON_CAPTION_CLASSNAME = "v-button-caption";

    public ButtonImpl(By by) {
        super(by);
    }

    @Override
    public String getCaption() {
        return $(byChain(by, className(BUTTON_CAPTION_CLASSNAME))).getText();
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .when(InstanceOfCases.hasType(Caption.class)).get(c ->
                        Objects.equals(getCaption(), c.getCaption()))
                .getMatch();
    }

    @Override
    public Button click() {
        impl.shouldBe(visible)
                .shouldNotHave(disabledClass)
                .click();
        return this;
    }
}