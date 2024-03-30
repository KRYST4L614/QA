package org.example.core;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class OKLoginPage implements BasePage {

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
    public OKLoginPage open() {
        Selenide.open("/");
        return this;
    }

    public OKLoginPage enterLogin(String login) {
        loginInputField.shouldBe(exist).setValue(login);
        return this;
    }

    public OKLoginPage enterPassword(String password) {
        passwordInputField.shouldBe(exist).setValue(password);
        return this;
    }

    public OKMainPage clickLoginButton() {
        loginButton.shouldBe(exist).click();
        return new OKMainPage();
    }

    public OKLoginPage checkoutQR() {
        qrNavigateBar.shouldBe(exist).click();
        return this;
    }

    public OKLoginPage checkoutLogin() {
        loginNavigateBar.shouldBe(exist).click();
        return this;
    }

    public boolean loginInputFieldIsDisplayed() {
        return loginInputField.shouldBe(exist).isDisplayed();
    }

    public boolean passwordInputFieldIsDisplayed() {
        return passwordInputField.shouldBe(exist).isDisplayed();
    }

    public boolean qrImageIsDisplayed() {
        return qrImage.shouldBe(exist).isDisplayed();
    }

    public OKLoginPage clickLoginWithQr() {
        loginWithQrButton.shouldBe(exist).click();
        return this;
    }
}