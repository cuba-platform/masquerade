package com.haulmont.masquerade.composite;

import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.base.Composite;
import com.haulmont.masquerade.components.*;


public class LoginWindow extends Composite<LoginWindow> {
    @Wire
    private TextField loginField;

    @Wire
    private PasswordField passwordField;

    @Wire(path = "rememberMeCheckBox")
    private CheckBox rememberMeCheckBox;

    @Wire
    private Button loginSubmitButton;

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
        return loginSubmitButton;
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