package org.example.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTestCallback implements BeforeAllCallback, AfterEachCallback {
    public static final String baseUrl = "https://ok.ru";
    public static final String browser = "chrome";

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        Configuration.baseUrl = baseUrl;
        Configuration.browser = browser;
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        closeWebDriver();
    }
}
