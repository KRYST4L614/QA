package org.example.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OKLoginPage implements BasePage {

    public static final String password = "technopolisPassword";
    public static final String login = "technopol75";

    private final SelenideElement passwordInputField = $(By.xpath("//*[@id=\"field_password\"]"));
    private final SelenideElement loginInputField = $(By.xpath("//*[@id=\"field_email\"]"));
    private final SelenideElement loginButton = $("input.button-pro.__wide");
    private final SelenideElement qrNavigateBar = $("a.filter_i.js-login-nav.js-login-qrCode");
    private final SelenideElement loginNavigateBar = $("a.filter_i.js-login-nav.js-login-login");
    private final SelenideElement qrImage = $("div.qr_code_image_wrapper");

    private final SelenideElement loginWithQrButton = $("a.button-pro.__wide.qr-code-button");

    @Override
    public OKLoginPage open() {
        Selenide.open("/");
        return this;
    }

    public OKLoginPage enterLogin(String login) {
        loginInputField.setValue(login);
        return this;
    }

    public OKLoginPage enterPassword(String password) {
        passwordInputField.setValue(password);
        return this;
    }

    public OKMainPage clickLoginButton() {
        loginButton.click();
        return new OKMainPage();
    }

    public OKLoginPage checkoutQR() {
        qrNavigateBar.click();
        return this;
    }

    public OKLoginPage QRImageShouldBeVisible() {
        qrImage.shouldBe(visible);
        return this;
    }

    public OKLoginPage checkoutLogin() {
        loginNavigateBar.click();
        return this;
    }

    public OKLoginPage loginInputShouldBeVisible() {
        loginInputField.shouldBe(visible);
        return this;
    }

    public OKLoginPage passwordInputShouldBeVisible() {
        passwordInputField.shouldBe(visible);
        return this;
    }

    public OKLoginPage clickLoginWithQr() {
        loginWithQrButton.click();
        return this;
    }
}
