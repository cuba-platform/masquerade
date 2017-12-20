package com.haulmont.masquerade.components.impl;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.components.DateTimeField;
import com.haulmont.masquerade.conditions.DateValue;
import com.haulmont.masquerade.conditions.SpecificCondition;
import com.haulmont.masquerade.conditions.TimeValue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Conditions.*;
import static com.haulmont.masquerade.Conditions.readonly;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.VaadinClassNames.readonlyClass;
import static com.haulmont.masquerade.sys.VaadinClassNames.requiredClass;
import static com.haulmont.masquerade.sys.matchers.ConditionCases.componentApply;
import static com.haulmont.masquerade.sys.matchers.InstanceOfCases.hasType;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;
import static org.openqa.selenium.By.cssSelector;

public class DateTimeFieldImpl extends AbstractComponent<DateTimeField> implements DateTimeField {

    public static final By DATEPART = cssSelector("div[class*='popupcalendar'] > input");
    public static final By TIMEPART = cssSelector("input[class*='maskedfield']");

    public DateTimeFieldImpl(By by) {
        super(by);
    }

    @SuppressWarnings("CodeBlock2Expr")
    @Override
    public boolean apply(SpecificCondition condition) {
        return componentApply(match(condition), impl)
                .when(eq(required)).get(() -> {
                    return impl.has(requiredClass);
                })
                .when(eq(readonly)).get(() -> {
                    return impl.has(readonlyClass);
                })
                .when(eq(editable)).get(() -> {
                    return !impl.has(readonlyClass);
                })
                .when(hasType(DateValue.class)).get(dv -> {
                    SelenideElement dateFieldImpl = $(byChain(by, DATEPART));
                    return dateFieldImpl.has(exactValue(dv.getExpectedValue()));
                })
                .when(hasType(TimeValue.class)).get(tv -> {
                    SelenideElement timeFieldImpl = $(byChain(by, TIMEPART));
                    return timeFieldImpl.has(exactValue(tv.getExpectedValue()));
                })
                .getMatch();
    }

    @Override
    public String getDateValue() {
        return $(byChain(by, DATEPART))
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

    @Override
    public DateTimeField setDateValue(String value) {
        SelenideElement dateFieldImpl = $(byChain(by, DATEPART));
        dateFieldImpl
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .click();

        dateFieldImpl.sendKeys(Keys.HOME, value);
        return this;
    }

    @Override
    public String getTimeValue() {
        return $(byChain(by, TIMEPART))
                .shouldBe(visible)
                .shouldBe(enabled)
                .getValue();
    }

    @Override
    public DateTimeField setTimeValue(String value) {
        SelenideElement timeFieldImpl = $(byChain(by, TIMEPART));

        timeFieldImpl
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotBe(readonly)
                .click();

        timeFieldImpl.sendKeys(Keys.HOME, value);
        return this;
    }
}