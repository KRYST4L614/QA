package org.example.tests;

import org.example.core.OKLoginPage;
import org.example.core.OKMainPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginWithMailTest extends BaseLoginTest {
    private final static String correctUserName = "technopol75 technopol75";
    @Test
    public void testUserCanLoginWithMailPass() {
        OKMainPage mainPage = loginPage
                .enterLogin(OKLoginPage.login)
                .enterPassword(OKLoginPage.password)
                .clickLoginButton();
        assertTrue(mainPage.checkNavSideBarUserName(correctUserName),
                String.format("User name on main page is not correct. Expected: %s, actual: %s",
                        correctUserName, mainPage.getNavSideBarUserName()));
    }
}
