package org.example.core;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    public static final String password = "technopolisPassword";
    public static final String login = "technopol75";
    private static final SelenideElement passwordInputField = $(By.xpath("//input[@id='field_password']"));
    private static final SelenideElement loginInputField = $(By.xpath("//input[@id='field_email']"));
    private static final SelenideElement loginButton = $(By.xpath("//input[@data-l='t,sign_in']"));
    private static final SelenideElement qrNavigateBar = $(By.xpath("//a[@data-l='t,qr_tab']"));
    private static final SelenideElement loginNavigateBar = $(By.xpath("//a[@data-l='t,login_tab']"));
    private static final SelenideElement qrImage = $(By.xpath("//img[@class='qr_code_image']"));
    private static final SelenideElement loginWithQrButton = $(By.xpath("//a[@data-l='t,get_qr']"));

    @Override
    public LoginPage open() {
        Selenide.open("/");
        return this;
    }

    public LoginPage enterLogin(String login) {
        loginInputFieldIsDisplayed();
        loginInputField.setValue(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInputFieldIsDisplayed();
        passwordInputField.setValue(password);
        return this;
    }

    public MainPage clickLoginButton() {
        loginButton.shouldBe(
                exist.because(
                        buildErrorMessage("Login button", "exist", this.getClass().getSimpleName())
                )).click();
        return new MainPage();
    }

    public LoginPage checkoutQR() {
        qrNavigateBar.shouldBe(
                exist.because(
                        buildErrorMessage("QR login navigate tab", "exist", this.getClass().getSimpleName())
                )).click();
        return this;
    }

    public LoginPage checkoutLogin() {
        loginNavigateBar.shouldBe(
                exist.because(
                        buildErrorMessage("Login navigate tab", "exist", this.getClass().getSimpleName())
                )).click();
        return this;
    }

    public boolean loginInputFieldIsDisplayed() {
        return loginInputField.shouldBe(
                exist.because(buildErrorMessage("Login input field", "exist", this.getClass().getSimpleName()))
        ).isDisplayed();
    }

    public boolean passwordInputFieldIsDisplayed() {
        return passwordInputField.shouldBe(
                exist.because(buildErrorMessage("Password input field", "exist", this.getClass().getSimpleName()))
        ).isDisplayed();
    }

    public boolean qrImageIsDisplayed() {
        return qrImage.shouldBe(
                exist.because(buildErrorMessage("QR image", "exist", this.getClass().getSimpleName()))
        ).isDisplayed();
    }

    public LoginPage clickLoginWithQr() {
        loginWithQrButton.shouldBe(
                exist.because(buildErrorMessage("Login with QR button", "exist", this.getClass().getSimpleName()))
        ).click();
        return this;
    }
}