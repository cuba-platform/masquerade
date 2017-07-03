package com.haulmont.masquerade.components.impl.fresh;

import com.haulmont.masquerade.components.Tree;
import com.haulmont.masquerade.components.impl.AbstractComponent;
import org.openqa.selenium.By;

public class TreeImpl extends AbstractComponent<Tree> implements Tree {
    public TreeImpl(By by) {
        super(by);
    }
}