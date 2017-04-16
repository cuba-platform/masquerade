package com.haulmont.masquerade;

import com.haulmont.masquerade.components.Untyped;
import com.haulmont.masquerade.composite.LoginWindow;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.haulmont.masquerade.Components.wire;
import static com.haulmont.masquerade.Conditions.*;
import static org.junit.Assert.assertNotNull;

public class LoginTest {
    @Test
    public void login() {
        open("http://localhost:8080/app");

        LoginWindow loginWindow = wire(LoginWindow.class);

        assertNotNull(loginWindow.getLoginField());
        assertNotNull(loginWindow.getPasswordField());

        loginWindow.getLoginField()
                .shouldBe(editable)
                .shouldBe(enabled);

        loginWindow.getLoginField().setValue("masquerade");
        loginWindow.getPasswordField().setValue("rulezzz");

        loginWindow.getRememberMeCheckBox().set();
        loginWindow.getRememberMeCheckBox().getCaption();

        loginWindow.getLoginButton()
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldHave(caption("Submit"));

        String caption = loginWindow.getLoginButton().getCaption();
        boolean enabled = loginWindow.getLoginButton().isEnabled();

        Untyped loginFormLayout = wire(Untyped.class, "loginFormLayout");
        loginFormLayout.shouldBe(visible);

        loginWindow.getLoginButton().click();
    }
}