package org.example.utils;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Utils {
    public static String buildErrorMessage(String element, String condition, String pageName) {
        return String.format("%s should be   %s on the %s", element, condition, pageName);
    }


    public static SelenideElement checkElementState(
            By locator,
            String elementName,
            WebElementCondition condition,
            String pageName
    ) {
        return $(locator).shouldBe(
                condition.because(buildErrorMessage(
                        elementName, condition.getName(), pageName
                ))
        );
    }
}
