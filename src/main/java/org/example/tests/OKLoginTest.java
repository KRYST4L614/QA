package org.example.tests;

import org.example.pages.OKLoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BaseTest.class})
public class OKLoginTest {

    private final OKLoginPage loginPage = new OKLoginPage();

    @BeforeEach
    public void openLoginPage() {
        loginPage.open();
    }

    @Test
    public void testCheckoutQrLogin() {
        loginPage
                .checkoutQR()
                .QRImageShouldBeVisible();
    }

    @Test
    public void testCheckoutLogin() {
        loginPage
                .checkoutLogin()
                .passwordInputShouldBeVisible()
                .loginInputShouldBeVisible();
    }

    @Test
    public void testClickEnterWithQrButton() {
        loginPage
                .clickLoginWithQr()
                .QRImageShouldBeVisible()
                .checkoutLogin();
    }

    @Test
    public void testUserCanLoginWithMailPass() {
        loginPage
                .enterLogin(OKLoginPage.login)
                .enterPassword(OKLoginPage.password)
                .clickLoginButton()
                .logout();
    }
}