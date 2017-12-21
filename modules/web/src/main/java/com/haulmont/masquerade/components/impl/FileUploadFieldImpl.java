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

import com.haulmont.masquerade.components.FileUploadField;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.haulmont.masquerade.Selectors.byChain;
import static com.haulmont.masquerade.sys.TagNames.INPUT;

public class FileUploadFieldImpl extends AbstractComponent<FileUploadField> implements FileUploadField {

    public static final By CLEAR_BUTTON = By.className("c-fileupload-clear");

    public FileUploadFieldImpl(By by) {
        super(by);
    }

    @Override
    public void upload(File file) {
        $(byChain(by, INPUT))
                .shouldBe(enabled)
                .uploadFile(file);
    }

    @Override
    public void clear() {
        $(byChain(by, CLEAR_BUTTON))
                .shouldBe(enabled)
                .click();
    }
}