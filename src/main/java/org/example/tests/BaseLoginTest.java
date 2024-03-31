package org.example.tests;

import org.example.core.LoginPage;
import org.example.tests.extensions.BaseTestCallback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BaseTestCallback.class})
public class BaseLoginTest {

    protected final static LoginPage loginPage = new LoginPage();
    @BeforeEach
    public void openLoginPage() {
        loginPage.open();
    }
}
