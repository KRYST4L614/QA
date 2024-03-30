package org.example.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputPasswordFieldVisibleTest extends BaseLoginTest {
    @Test
    public void testInputPasswordFiledVisible() {
        assertTrue(loginPage.passwordInputFieldIsDisplayed(),
                "Password input field is not visible on Login Page");
    }
}
