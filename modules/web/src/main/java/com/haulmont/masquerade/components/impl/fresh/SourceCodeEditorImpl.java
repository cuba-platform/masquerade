package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.SourceCodeEditor;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

public class SourceCodeEditorImpl extends AbstractComponent<SourceCodeEditor> implements SourceCodeEditor {
    public SourceCodeEditorImpl(By by) {
        super(by);
    }
}
