package com.haulmont.masquerade;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class Selectors extends com.codeborne.selenide.Selectors {
    public static final String CUBA_ID_ATTRIBUTE_NAME = "cuba-id";

    public static By byCubaId(String cubaId) {
        return byAttribute(CUBA_ID_ATTRIBUTE_NAME, cubaId);
    }

    public static By byChain(By rootBy, By pathBy) {
        return new ByChained(rootBy, pathBy);
    }
}