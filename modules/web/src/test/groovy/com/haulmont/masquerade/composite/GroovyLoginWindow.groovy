package com.haulmont.masquerade.composite

import com.haulmont.masquerade.Wire
import com.haulmont.masquerade.base.Composite
import com.haulmont.masquerade.components.Button
import com.haulmont.masquerade.components.CheckBox
import com.haulmont.masquerade.components.PasswordField
import com.haulmont.masquerade.components.TextField

class GroovyLoginWindow extends Composite<GroovyLoginWindow> {
    @Wire
    TextField loginField

    @Wire
    PasswordField passwordField

    @Wire
    Button loginButton

    @Wire
    CheckBox rememberMeCheckBox
}