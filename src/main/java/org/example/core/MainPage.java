package org.example.core;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    private static final SelenideElement navSideBar = $(By.xpath("//div[@id='hook_Block_AsideColumn']"));
    private static final SelenideElement navSideBarUserName =
            navSideBar.$(By.xpath(".//a[@data-l='t,userPage']/div"));
    private static final SelenideElement toolbarMenuBtn =
            $(By.xpath("//button[@aria-controls='user-dropdown-menu']"));
    private static final SelenideElement logoutBtn = $(By.xpath("//a[@data-l='t,logout'"));
    private static final SelenideElement confirmLogoutBtn =
            $(By.xpath("//input[@name='logoff.confirm_not_decorate'"));

    @Override
    public MainPage open() {
        Selenide.open("/");
        return this;
    }
    public boolean checkNavSideBarUserName(String expectedName) {
        return getNavSideBarUserName().equals(expectedName);
    }

    public String getNavSideBarUserName() {
        return navSideBarUserName.shouldBe(visible.because(buildErrorMessage(
                "User name in navigate side bar", "visible", this.getClass().getSimpleName()))
        ).text();
    }

    public LoginPage logout() {
        toolbarMenuBtn.shouldBe(exist.because(buildErrorMessage(
                "Toolbar menu button", "exist", this.getClass().getSimpleName()))
        ).click();
        logoutBtn.shouldBe(exist.because(buildErrorMessage(
                "Logout button", "exist", this.getClass().getSimpleName()))
        ).click();
        confirmLogoutBtn.shouldBe(exist.because(buildErrorMessage(
                "Confirm logout button", "exist", this.getClass().getSimpleName()))
        ).click();
        return new LoginPage().open();
    }
}
