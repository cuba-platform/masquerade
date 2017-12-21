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

package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.Components;
import com.haulmont.masquerade.components.AppMenu;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.Selectors.byCubaId;

public class AppMenuImpl extends AbstractComponent<AppMenu> implements AppMenu {

    public static final String MENUITEM_CAPTION_CLASS = "v-menubar-menuitem-caption";

    public AppMenuImpl(By by) {
        super(by);
    }

    @Override
    public <T> T openItem(Class<T> clazz, String... path) {
        openItem(path);
        return Components.wire(clazz);
    }

    @Override
    public <T> T openItem(Menu<T> menu) {
        openItem(menu.getPath());
        return Components.wire(menu.getScreenClass());
    }

    @Override
    public void openItem(String... path) {
        for (String s : path) {
            $(byChain(byCubaId(s), byClassName(MENUITEM_CAPTION_CLASS)))
                    .shouldBe(visible)
                    .shouldBe(enabled)
                    .click();
        }
    }
}