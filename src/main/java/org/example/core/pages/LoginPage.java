package org.example.core.pages;

import org.example.core.valueObjects.User;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.example.utils.Utils.checkElementState;

public class LoginPage implements LoadablePage {

    public static final User user = new User("technopol75", "technopolisPassword");
    private static final By passwordInputField = By.xpath("//input[@id='field_password']");
    private static final By loginInputField = By.xpath("//input[@id='field_email']");
    private static final By loginButton = By.xpath("//input[@data-l='t,sign_in']");
    private static final By qrNavigateBar = By.xpath("//a[@data-l='t,qr_tab']");
    private static final By loginNavigateBar = By.xpath("//a[@data-l='t,login_tab']");
    private static final By loginWithQrButton = By.xpath("//a[@data-l='t,get_qr']");
    private static final By loginErrorText = By.xpath("//div[@class='input-e login_error']");
    private static final By loginInputFieldFailed =
            By.xpath("//div[contains(@class, 'anonym_login_field')]");
    private static final By passwordInputFieldFailed =
            By.xpath("//div[contains(@class, 'anonym_password_field')]");

    public LoginPage() {
        checkPage();
    }


    public LoginPage enterLogin(String login) {
        checkElementState(loginInputField, "Login input field", visible, this.getClass().getSimpleName())
                .setValue(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        checkElementState(passwordInputField, "Password input field", visible, this.getClass().getSimpleName())
                .setValue(password);
        return this;
    }

    public Boolean checkErrorLogin() {
        return $(loginErrorText).isDisplayed() && $(loginInputFieldFailed).isDisplayed()
                && $(passwordInputFieldFailed).isDisplayed();
    }

    public void clickLoginButton() {
        checkElementState(loginButton, "Login button", visible, this.getClass().getSimpleName()).click();
    }

    @Override
    public void checkPage() {
        checkElementState(passwordInputField, "Password input field", visible, this.getClass().getSimpleName());
        checkElementState(loginInputField, "Login input field", visible, this.getClass().getSimpleName());
        checkElementState(loginButton, "Login button", visible, this.getClass().getSimpleName());
        checkElementState(qrNavigateBar, "QR navigate bar", visible, this.getClass().getSimpleName());
        checkElementState(loginNavigateBar, "Login navigate bar", visible, this.getClass().getSimpleName());
        checkElementState(loginWithQrButton, "Login with QR button", visible, this.getClass().getSimpleName());
        checkElementState(loginErrorText, "Login error text", hidden, this.getClass().getSimpleName());
    }
}