package org.example.tests;

import org.example.core.pages.FeedPage;
import org.example.core.pages.LoginPage;
import org.example.core.steps.LoginSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginWithMailTest extends BaseTest {
    private final LoginSteps steps = new LoginSteps();
    private final static String correctUserName = "technopol75 technopol75";

    @BeforeEach
    public void setup() {
        steps.prepareTest();
    }

    @Test
    public void testUserCanLoginWithMailPass() {
        steps.login(LoginPage.user);
        FeedPage feedPage = new FeedPage();
        assertEquals(feedPage.getNavSideBarUserName(), correctUserName,
                "User name on main page is not correct");
    }
}
