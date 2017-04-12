package com.haulmont.masquerade;

import org.openqa.selenium.By;

public class Selectors extends com.codeborne.selenide.Selectors {
    public static By byCubaId(String cubaId) {
        return byAttribute("cuba-id", cubaId);
    }
}