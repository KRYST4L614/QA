package org.example.tests;

import org.example.core.steps.LoginSteps;
import org.example.core.valueObjects.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BadPassLoginWithMailTest extends BaseTest {
    private final LoginSteps steps = new LoginSteps();

    @BeforeEach
    public void setup() {
        steps.prepareTest();
    }

    @ParameterizedTest(name = "{index}: Test login with mail = {0} and password = {1}")
    @Tag("badpass")
    @MethodSource("provideMailAndPasswords")
    public void testLoginWithWrongDataTest(User user) {
        steps.login(user);
        assertTrue(steps.getLoginPage().checkErrorLogin(), "Error login text should be visible during invalid login" +
                " and/or password");
    }

    static Stream<Arguments> provideMailAndPasswords() {
        return Stream.of(
                Arguments.of(new User("BadLogin", "BadPassword")),
                Arguments.of(new User("", "")),
                Arguments.of(new User(" ", " ")),
                Arguments.of(new User("*!%4$&*()#@", "@!@&#$*%()^_@!"))
        );
    }
}
