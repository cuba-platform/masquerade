package com.haulmont.masquerade;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import static com.google.common.base.Preconditions.checkNotNull;

public class Selectors extends com.codeborne.selenide.Selectors {
    public static final String CUBA_ID_ATTRIBUTE_NAME = "cuba-id";

    public static By byPath(String... path) {
        checkNotNull(path);

        By[] bys = new By[path.length];

        for (int i = 0; i < path.length; i++) {
            bys[i] = byCubaId(path[i]);
        }

        if (bys.length == 1) {
            return bys[0];
        }

        return byChain(bys);
    }

    public static By byCubaId(String cubaId) {
        checkNotNull(cubaId);

        return byAttribute(CUBA_ID_ATTRIBUTE_NAME, cubaId);
    }

    public static By byChain(By... bys) {
        checkNotNull(bys);

        return new ByChained(bys);
    }
}