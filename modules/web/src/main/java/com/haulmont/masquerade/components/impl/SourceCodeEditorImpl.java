package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.SourceCodeEditor;
import org.openqa.selenium.By;

public class SourceCodeEditorImpl extends AbstractComponent<SourceCodeEditor> implements SourceCodeEditor {
    public SourceCodeEditorImpl(By by) {
        super(by);
    }
}
