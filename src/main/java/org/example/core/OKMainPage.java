package org.example.core;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OKMainPage implements BasePage {
    private static final SelenideElement navSideBar = $(By.xpath("//div[@id='hook_Block_AsideColumn']"));
    private static final SelenideElement navSideBarUserName = navSideBar.$(By.xpath(".//a[@data-l='t,userPage']/div"));
    private static final SelenideElement toolbarMenuBtn =
            $(By.xpath("//button[@aria-controls='user-dropdown-menu']"));
    private static final SelenideElement logoutBtn = $(By.xpath("//a[@data-l='t,logout'"));
    private static final SelenideElement confirmLogoutBtn = $(By.xpath("//input[@name='logoff.confirm_not_decorate'"));

    @Override
    public OKMainPage open() {
        Selenide.open("/");
        return this;
    }
    public boolean checkNavSideBarUserName(String expectedName) {
        return navSideBarUserName.shouldBe(visible).text().equals(expectedName);
    }

    public String getNavSideBarUserName() {
        return navSideBarUserName.shouldBe(visible).text();
    }

    public OKLoginPage logout() {
        toolbarMenuBtn.shouldBe(exist).click();
        logoutBtn.shouldBe(exist).click();
        confirmLogoutBtn.shouldBe(exist).click();
        return new OKLoginPage().open();
    }
}
