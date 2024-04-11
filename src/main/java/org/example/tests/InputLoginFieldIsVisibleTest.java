package org.example.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputLoginFieldIsVisibleTest extends BaseLoginTest {
    @Test
    public void testInputLoginFieldIsVisible() {
        assertTrue(loginPage.loginInputFieldIsDisplayed(),
                "Login input field is not visible on Login Page");
    }
}
