package org.example.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BadPassLoginWithMailTest extends BaseLoginTest {
    @ParameterizedTest(name = "{index}: Test login with mail = {0} and password = {1}")
    @Tag("badpass")
    @MethodSource("provideMailAndPasswords")
    public void testLoginWithWrongDataTest(String mail, String password) {
        loginPage
                .enterLogin(mail)
                .enterPassword(password)
                .clickLoginButton();
        assertAll("Check error marks is visible",
                () -> assertTrue(loginPage.errorLoginTextIsVisible(), "Error login text is not visible"),
                () -> assertTrue(loginPage.loginInputFieldFailedIsVisible(), "Red line around login input field is not visible "),
                () -> assertTrue(loginPage.passwordInputFieldIsDisplayed(), "Red line around password input field is not visible")
        );
    }

    static Stream<Arguments> provideMailAndPasswords() {
        return Stream.of(
                Arguments.of("BadLogin", "BadPassword"),
                Arguments.of("", ""),
                Arguments.of(" ", " "),
                Arguments.of("*!%4$&*()#@", "@!@&#$*%()^_@!")
        );
    }
}
