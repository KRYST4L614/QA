package org.example.core;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {

    protected static String buildErrorMessage(String element, String condition, String pageName) {
        return String.format("%s is not %s on the %s", element, condition, pageName);
    }
    abstract BasePage open();

    protected SelenideElement checkElementState(By locator, WebElementCondition condition, String elementName) {
        return $(locator).shouldBe(
                condition.because(buildErrorMessage(
                        elementName, condition.getName(), this.getClass().getSimpleName()
                ))
        );
    }
}
