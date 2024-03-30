package org.example.tests;

import org.example.core.OKLoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BaseTestCallback.class})
public class BaseLoginTest {

    protected final static OKLoginPage loginPage = new OKLoginPage();
    @BeforeEach
    public void openLoginPage() {
        loginPage.open();
    }
}
