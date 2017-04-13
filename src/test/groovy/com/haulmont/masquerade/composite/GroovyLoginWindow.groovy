package com.haulmont.masquerade.composite

import com.haulmont.masquerade.Connect
import com.haulmont.masquerade.components.Button
import com.haulmont.masquerade.components.PasswordField
import com.haulmont.masquerade.components.TextField

class GroovyLoginWindow {
    @Connect
    TextField loginField

    @Connect
    PasswordField passwordField

    @Connect
    Button loginButton
}