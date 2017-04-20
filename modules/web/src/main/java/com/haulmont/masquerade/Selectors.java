package com.haulmont.masquerade;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.singletonList;

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

    public static By byTarget(SelenideElement target) {
        return new ByTarget(target);
    }

    public static class ByTarget extends By {
        private final SelenideElement target;

        public ByTarget(SelenideElement target) {
            this.target = target;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            return singletonList(target.getWrappedElement());
        }
    }

    // todo components aware selectors: byCaption, ...
}