/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.masquerade.composite;

import com.haulmont.masquerade.Wire;
import com.haulmont.masquerade.base.Composite;
import com.haulmont.masquerade.components.*;
import org.openqa.selenium.support.FindBy;


public class LoginWindow extends Composite<LoginWindow> {
    @Wire
    private TextField loginField;

    @Wire
    private PasswordField passwordField;

    @Wire(path = "rememberMeCheckBox")
    private CheckBox rememberMeCheckBox;

    @Wire(path = {"loginFormLayout", "loginButton"})
    private Button loginSubmitButton;

    @Wire
    private LookupField localesSelect;

    @Wire
    private Label welcomeLabel;

    @FindBy(className = "c-login-caption")
    private Label welcomeLabelTest;

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

    public Label getWelcomeLabelTest() {
        return welcomeLabelTest;
    }
}