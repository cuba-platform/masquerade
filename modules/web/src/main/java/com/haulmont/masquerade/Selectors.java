/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.masquerade;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.singletonList;

public class Selectors extends com.codeborne.selenide.Selectors {
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

    public static By byCells(String... cellValues) {
        return new ByCells(cellValues);
    }

    public static By byIndex(int index) {
        return new ByIndex(index);
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

    public static class ByIndex extends By {

        private final int index;

        public ByIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            throw new RuntimeException(
                    "ByIndex must be checked ony in Component implementations");
        }

        @Override
        public String toString() {
            return "By.index: " + index;
        }
    }

    public static class ByCells extends By {
        private final String[] cellValues;

        public ByCells(String[] cellValues) {
            this.cellValues = cellValues;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            throw new RuntimeException(
                    "ByCells must be checked ony in Table implementations");
        }

        public String[] getCellValues() {
            return cellValues;
        }

        @Override
        public String toString() {
            return "By.cells: " + StringUtils.join(cellValues, ',');
        }
    }
}