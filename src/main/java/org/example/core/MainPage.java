package org.example.core;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;

public class MainPage extends BasePage {
    private static final By navSideBarUserName =
            By.xpath("//div[@id='hook_Block_AsideColumn']//a[@data-l='t,userPage']/div");

    @Override
    public MainPage open() {
        Selenide.open("/");
        return this;
    }

    public boolean checkNavSideBarUserName(String expectedName) {
        return getNavSideBarUserName().equals(expectedName);
    }

    public String getNavSideBarUserName() {
        return checkElementState(navSideBarUserName, visible, "User name in navigate side bar").text();
    }
}
