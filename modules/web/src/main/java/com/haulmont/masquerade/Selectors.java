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

    protected Selectors() {
    }

    public static By byPath(String... path) {
        checkNotNull(path);

        if (path.length == 1) {
            return byCubaId(path[0]);
        }

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

        return new ByCubaId(cubaId);
    }

    public static By byChain(By... bys) {
        checkNotNull(bys);

        return new ByChain(bys);
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

    public static class ByCubaId extends By.ByCssSelector {
        private final String cubaId;

        public ByCubaId(String cubaId) {
            super(String.format("[cuba-id='%s']", cubaId));

            this.cubaId = cubaId;
        }

        public String getCubaId() {
            return cubaId;
        }
    }

    public static class ByChain extends ByChained {
        private By[] bys;

        public ByChain(By... bys) {
            super(bys);
            this.bys = bys;
        }

        public By[] getBys() {
            return bys;
        }

        public By getLastBy() {
            return bys[bys.length - 1];
        }
    }

    // todo components aware selectors: byCaption, ...
}