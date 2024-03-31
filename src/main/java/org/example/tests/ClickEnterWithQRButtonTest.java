package org.example.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClickEnterWithQRButtonTest extends BaseLoginTest {
    @Test
    public void testClickEnterWithQrButton() {
        loginPage
                .clickLoginWithQr();
        assertTrue(loginPage.qrImageIsDisplayed(),
                "QR image is not visible on login page after click button \"Login with QR\"");
    }
}
