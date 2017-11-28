package com.haulmont.masquerade

import com.haulmont.masquerade.composite.GroovyLoginWindow
import org.junit.Ignore
import org.junit.Test

import static com.codeborne.selenide.Selenide.open
import static com.haulmont.masquerade.Components.wire
import static org.junit.Assert.assertNotNull

@Ignore
class GroovyLoginTest {
    @Test
    void login() {
        open("http://localhost:8080/app")

        def loginWindow = wire(GroovyLoginWindow, 'loginMainBox')

        assertNotNull(loginWindow.loginField)
        assertNotNull(loginWindow.passwordField)

        loginWindow.with {
            rememberMeCheckBox.checked = true
            rememberMeCheckBox.checked = false

            loginField.value = "masquerade"
            passwordField.value = "rulezzz"

            loginButton.click()
        }
    }
}