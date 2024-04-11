package org.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    public static final String baseUrl = "https://ok.ru";
    public static final String browser = "chrome";

    @BeforeEach
    public void beforeEach() {
        Configuration.baseUrl = baseUrl;
        Configuration.browser = browser;
        Selenide.open("/");
    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
