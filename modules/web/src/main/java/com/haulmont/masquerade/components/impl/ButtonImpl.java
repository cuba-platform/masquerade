package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.components.Button;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.VaadinClassNames.disabledClass;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.*;
import static com.haulmont.masquerade.sys.matchers.Matchers.matchAll;
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
    public boolean has(Condition condition) {
        return componentHas(match(condition), impl)
                .when(isCaption()).get(c->
                        Objects.equals(getCaption(), c.getCaption()))
                .orElse(c -> Button.super.has(condition))
                .getMatch();
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public Button should(Condition... conditions) {
        matchAll(conditions, m -> componentShould(m, impl)
                .when(isCaption()).then(c -> {
                    $(byChain(by, className(BUTTON_CAPTION_CLASSNAME)))
                            .shouldHave(exactTextCaseSensitive(c.getCaption()));
                })
                .orElse(c -> Button.super.should(c)));

        return this;
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public Button shouldNot(Condition... conditions) {
        matchAll(conditions, m -> componentShouldNot(m, impl)
                .when(isCaption()).then(c -> {
                    $(byChain(by, className(BUTTON_CAPTION_CLASSNAME)))
                            .shouldNotHave(exactTextCaseSensitive(c.getCaption()));
                })
                .orElse(c -> Button.super.shouldNot(c)));

        return this;
    }

    @Override
    public Button click() {
        impl.shouldBe(visible)
                .shouldNotHave(disabledClass)
                .click();
        return this;
    }
}