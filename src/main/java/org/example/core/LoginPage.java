package org.example.core;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    public static final String password = "technopolisPassword";
    public static final String login = "technopol75";
    private static final By passwordInputField = By.xpath("//input[@id='field_password']");
    private static final By loginInputField = By.xpath("//input[@id='field_email']");
    private static final By loginButton = By.xpath("//input[@data-l='t,sign_in']");
    private static final By qrNavigateBar = By.xpath("//a[@data-l='t,qr_tab']");
    private static final By loginNavigateBar = By.xpath("//a[@data-l='t,login_tab']");
    private static final By qrImage = By.xpath("//img[@class='qr_code_image']");
    private static final By loginWithQrButton = By.xpath("//a[@data-l='t,get_qr']");
    private static final By loginErrorText = By.xpath("//div[@class='input-e login_error']");
    private static final By loginInputFieldFailed =
            By.xpath("//div[@class='form_i form_i__error anonym_login_field']");
    private static final By passwordInputFieldFailed =
            By.xpath("//div[@class='form_i form_i__error anonym_password_field']");

    @Override
    public LoginPage open() {
        Selenide.open("/");
        return this;
    }

    public LoginPage enterLogin(String login) {
        loginInputFieldIsDisplayed();
        $(loginInputField).setValue(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInputFieldIsDisplayed();
        $(passwordInputField).setValue(password);
        return this;
    }

    public MainPage clickLoginButton() {
        checkElementState(loginButton, visible, "Login button").click();
        return new MainPage();
    }

    public LoginPage checkoutQR() {
        checkElementState(qrNavigateBar, visible, "QR login navigate tab").click();
        return this;
    }

    public LoginPage checkoutLogin() {
        checkElementState(loginNavigateBar, visible, "Login navigate tab").click();
        return this;
    }

    public boolean loginInputFieldIsDisplayed() {
        return checkElementState(loginInputField, exist, "Login input field").isDisplayed();
    }

    public boolean passwordInputFieldIsDisplayed() {
        return checkElementState(passwordInputField, exist, "Password input field").isDisplayed();
    }

    public boolean qrImageIsDisplayed() {
        return checkElementState(qrImage, exist, "QR image").isDisplayed();
    }

    public LoginPage clickLoginWithQr() {
        checkElementState(loginWithQrButton, visible, "Login with QR button").click();
        return this;
    }

    public boolean loginInputFieldFailedIsVisible() {
        return checkElementState(loginInputFieldFailed, exist, "Login input field failed").isDisplayed();
    }

    public boolean loginPasswordFieldFailedIsVisible() {
        return checkElementState(passwordInputFieldFailed, exist, "Password input field failed").isDisplayed();
    }

    public boolean errorLoginTextIsVisible() {
        return checkElementState(loginErrorText, exist, "Login error text").isDisplayed();
    }
}