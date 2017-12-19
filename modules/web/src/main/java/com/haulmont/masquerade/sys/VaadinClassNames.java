package com.haulmont.masquerade.sys;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.cssClass;

public final class VaadinClassNames {
    public static final String DISABLED_CLASSNAME = "v-disabled";
    public static final String READONLY_CLASSNAME = "v-readonly";
    public static final String REQUIRED_CLASSNAME = "v-required";

    public static final Condition disabledClass = cssClass(DISABLED_CLASSNAME);
    public static final Condition readonlyClass = cssClass(READONLY_CLASSNAME);
    public static final Condition requiredClass = cssClass(REQUIRED_CLASSNAME);

    private VaadinClassNames() {
    }
}