package org.example.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutQRLoginTest extends BaseLoginTest {
    @Test
    public void testCheckoutQrLogin() {
        loginPage
                .checkoutQR();
        assertTrue(loginPage.qrImageIsDisplayed(),
                "QR image is not visible on login page after choose login with QR code");
    }
}
