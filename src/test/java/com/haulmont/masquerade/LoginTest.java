package com.haulmont.masquerade;

import com.haulmont.masquerade.composite.LoginWindow;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.haulmont.masquerade.Masquerade.mask;
import static org.junit.Assert.assertNotNull;

public class LoginTest {
    @Test
    public void login() {
        open("http://localhost:8080/app");

        LoginWindow loginWindow = mask("loginMainBox").as(LoginWindow.class);

        assertNotNull(loginWindow.getLoginField());
        assertNotNull(loginWindow.getPasswordField());

        loginWindow.getLoginField().setValue("masquerade");
        loginWindow.getPasswordField().setValue("rulezzz");
    }
}