package org.example.core.elements;

import org.example.core.pages.LoadablePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static org.example.utils.Utils.checkElementState;

public class SideNavigationBarElement implements LoadablePage {
    private static final By navSideBarUserName =
            By.xpath("//*[@data-l='t,userPage']/div");

    public SideNavigationBarElement() {
        checkPage();
    }


    public String getNavSideBarUserName() {
        return checkElementState(navSideBarUserName, "User name in navigation side bar", visible, this.getClass().getSimpleName())
                .getText();
    }

    @Override
    public void checkPage() {
        checkElementState(navSideBarUserName, "User name in navigation side bar", visible, this.getClass().getSimpleName());
    }
}
