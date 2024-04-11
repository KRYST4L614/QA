package org.example.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutMailLoginTest extends BaseLoginTest {
    @Test
    public void testCheckoutLogin() {
        loginPage
                .checkoutLogin();
        assertTrue(loginPage.loginInputFieldIsDisplayed(),
                "Login input field is not visible after checkout login by email");
        assertTrue(loginPage.passwordInputFieldIsDisplayed(),
                "Password input field is not visible after checkout login by email");
    }
}
