package com.haulmont.masquerade.composite;

import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.base.Composite;
import com.haulmont.masquerade.components.Button;
import com.haulmont.masquerade.components.PasswordField;
import com.haulmont.masquerade.components.TextField;

@Wire(path = "loginMainBox")
public class LoginWindow extends Composite<LoginWindow> {
    @Wire
    private TextField loginField;

    @Wire
    private PasswordField passwordField;

    @Wire(path = {"loginFormLayout", "loginButton"})
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