package com.haulmont.masquerade.composite;

import com.haulmont.masquerade.Connect;
import com.haulmont.masquerade.components.PasswordField;
import com.haulmont.masquerade.components.TextField;

public class LoginWindow {
    @Connect
    private TextField loginField;

    @Connect
    private PasswordField passwordField;

    public TextField getLoginField() {
        return loginField;
    }

    public void setLoginField(TextField loginField) {
        this.loginField = loginField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }
}