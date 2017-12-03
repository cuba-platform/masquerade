package com.haulmont.masquerade.components.impl;

import com.haulmont.masquerade.components.Tree;
import org.openqa.selenium.By;

public class TreeImpl extends AbstractComponent<Tree> implements Tree {
    public TreeImpl(By by) {
        super(by);
    }
}