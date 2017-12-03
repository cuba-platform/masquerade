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