package com.haulmont.masquerade.composite;

import com.haulmont.masquerade.Connect;
import com.haulmont.masquerade.components.Button;
import com.haulmont.masquerade.components.PasswordField;
import com.haulmont.masquerade.components.TextField;

public class LoginWindow {
    @Connect
    private TextField loginField;

    @Connect
    private PasswordField passwordField;

    @Connect
    private Button loginButton;

    public TextField getLoginField() {
        return loginField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }
}