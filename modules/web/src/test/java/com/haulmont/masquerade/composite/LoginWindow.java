package com.haulmont.masquerade.composite;

import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.base.Composite;
import com.haulmont.masquerade.components.*;

@Wire(path = "loginMainBox")
public class LoginWindow extends Composite<LoginWindow> {
    @Wire
    private TextField loginField;

    @Wire
    private PasswordField passwordField;

    @Wire(path = "rememberMeCheckBox")
    private CheckBox rememberMeCheckBox;

    @Wire(path = {"loginFormLayout", "loginButton"})
    private Button loginButton;

    @Wire
    private LookupField localesSelect;

    @Wire
    private Label welcomeLabel;

    public TextField getLoginField() {
        return loginField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public CheckBox getRememberMeCheckBox() {
        return rememberMeCheckBox;
    }

    public LookupField getLocalesSelect() {
        return localesSelect;
    }

    public Label getWelcomeLabel() {
        return welcomeLabel;
    }
}