package org.example.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OKMainPage implements BasePage {

    private final SelenideElement navSideBar = $("div.nav-side.__navigation.__user-main");
    private final SelenideElement toolbarMenuBtn =
            $("button.ucard-mini.toolbar_ucard.js-toolbar-menu.__a11y");
    private final SelenideElement logoutBtn = $("div.toolbar_accounts-user-delete-button");
    private final SelenideElement confirmLogoutBtn = $(By.id("hook_FormButton_logoff.confirm_not_decorate"));

    @Override
    public OKMainPage open() {
        Selenide.open("/");
        return this;
    }

    public OKMainPage navSideBarShouldBeVisible() {
        navSideBar.shouldBe(visible);
        return this;
    }

    public OKLoginPage logout() {
        toolbarMenuBtn.click();
        logoutBtn.click();
        confirmLogoutBtn.click();
        return new OKLoginPage().open();
    }

}
