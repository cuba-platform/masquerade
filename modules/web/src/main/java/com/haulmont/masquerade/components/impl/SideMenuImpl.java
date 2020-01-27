package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.Conditions;
import com.haulmont.masquerade.components.Button;
import com.haulmont.masquerade.components.SideMenu;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.leacox.motif.MatchesExact;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Quotes;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static com.haulmont.masquerade.Selectors.$c;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byClassName;
import static com.haulmont.masquerade.Selectors.byCubaId;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SideMenuImpl extends AbstractComponent<SideMenu> implements SideMenu {

    public static final String SIDE_MENU_CONTAINER_CLASS_NAME = "c-sidemenu-container";
    public static final String COLLAPSED_CLASS_NAME = "collapsed";

    public static final By COLLAPSE_BUTTON = byCubaId("collapseMenuButton");

    public SideMenuImpl(By by) {
        super(by);
    }

    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), getDelegate())
                .when(MatchesExact.eq(Conditions.EXPANDED)).get(this::isExpanded)
                .when(MatchesExact.eq(Conditions.COLLAPSED)).get(this::isCollapsed)
                .getMatch();
    }

    @Override
    public <T> T openItem(Class<T> screenClass, String... path) {
        openItem(path);
        return Components.wire(screenClass);
    }

    @Override
    public <T> T openItem(Menu<T> menu) {
        openItem(menu.getPath());
        return Components.wire(menu.getScreenClass());
    }

    @Override
    public void openItem(String... path) {
        for (String s : path) {
            String itemXpath = "//div[contains(@class, 'c-sidemenu-item') " +
                    "and @cuba-id=" + Quotes.escape(s) + "]";

            SelenideElement menuItemElement = $(byXpath(itemXpath));

            menuItemElement
                    .shouldBe(visible)
                    .shouldBe(enabled);

            Wait().until(elementToBeClickable(menuItemElement));

            menuItemElement.click();
        }
    }

    @Override
    public SideMenu expand() {
        if (!isExpanded()) {
            toggleCollapsed();
        }

        return this;
    }

    @Override
    public SideMenu collapse() {
        if (!isCollapsed()) {
            toggleCollapsed();
        }

        return this;
    }

    protected boolean isCollapsed() {
        return $(byChain(by, byClassName(SIDE_MENU_CONTAINER_CLASS_NAME)))
                .shouldBe(visible)
                .has(cssClass(COLLAPSED_CLASS_NAME));
    }

    protected boolean isExpanded() {
        return !isCollapsed();
    }

    protected void toggleCollapsed() {
        $c(Button.class, COLLAPSE_BUTTON)
                .shouldBe(visible)
                .click();
    }
}
