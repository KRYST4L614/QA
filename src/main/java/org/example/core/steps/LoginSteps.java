package org.example.core.steps;

import org.example.core.pages.LoginPage;
import org.example.core.valueObjects.User;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps prepareTest() {
        loginPage = new LoginPage();
        return this;
    }

    public LoginSteps login(User user) {
        loginPage
                .enterLogin(user.login())
                .enterPassword(user.password())
                .clickLoginButton();
        return this;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
}
