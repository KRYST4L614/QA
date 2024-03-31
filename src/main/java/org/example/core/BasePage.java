package org.example.core;

public abstract class BasePage {

    protected static String buildErrorMessage(String element, String condition, String pageName) {
        return String.format("%s is not %s on the %s", element, condition, pageName);
    }
    abstract BasePage open();
}
