package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.Condition;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.components.GroupBox;
import com.haulmont.masquerade.conditions.Caption;
import com.haulmont.masquerade.conditions.SpecificCondition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class GroupBoxImpl extends AbstractComponent<GroupBox> implements GroupBox {

    public static final String EXPANDED = "expanded";

    public static final By EXPANDER = cssSelector("div[class*='captionwrap'] span[class*='expander']");
    public static final By CAPTION_TEXT = cssSelector("div[class*='captionwrap'] span[class*='caption-text']");

    public GroupBoxImpl(By by) {
        super(by);
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .when(eq(Conditions.expanded)).get(() -> {
                    return $(byChain(by, EXPANDER)).has(cssClass(EXPANDED));
                })
                .when(eq(Conditions.collapsed)).get(() -> {
                    return !$(byChain(by, EXPANDER)).has(cssClass(EXPANDED));
                })
                .when(hasType(Caption.class)).get(c -> {
                    return $(byChain(by, CAPTION_TEXT)).has(exactText(c.getCaption()));
                })
                .getMatch();
    }

    @Override
    public void collapse() {
        $(byChain(by, EXPANDER))
                .shouldHave(cssClass(EXPANDED))
                .shouldBe(Condition.visible)
                .click();
    }

    @Override
    public void expand() {
        $(byChain(by, EXPANDER))
                .shouldNotHave(cssClass(EXPANDED))
                .shouldBe(Condition.visible)
                .click();
    }

    @Override
    public String getCaption() {
        return $(byChain(by, CAPTION_TEXT))
                .shouldBe(visible)
                .text();
    }
}