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

package com.haulmont.masquerade.components;

import com.codeborne.selenide.SelenideElement;
import com.haulmont.masquerade.Selectors;
import org.openqa.selenium.By;

public interface Tree extends Component<Tree> {

    /**
     * Obtain reference to Tree node.
     * <br>
     * Supported bys:
     * <ul>
     * <li>{@link Selectors#byText(String)}</li>
     * <li>{@link Selectors#withText(String)}</li>
     * <li>{@link Selectors#byClassName(String)}</li>
     * <li>{@link Selectors#byRowIndex(int)} </li>
     * </ul>
     *
     * @param nodeBy node selector
     * @return selenide element
     */
    SelenideElement getNode(By nodeBy);

    /* todo
    void select(By nodeBy);

    void expand(By nodeBy);

    void collapse(By nodeBy);
    */
}