package org.example.core.elements;

import org.example.core.pages.LoadablePage;
import org.example.core.pages.MessagePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static org.example.utils.Utils.checkElementState;

public class NavigationToolbarElement implements LoadablePage {
    private static final By msgToolBarButton = By.xpath("//*[@id='msg_toolbar_button']");

    public NavigationToolbarElement() {
        checkPage();
    }

    public MessagePage clickOnMessageButton() {
        checkElementState(msgToolBarButton, "Message toolbar button", visible, this.getClass().getSimpleName()).click();
        return new MessagePage();
    }

    @Override
    public void checkPage() {
        checkElementState(msgToolBarButton, "Message toolbar button", visible, this.getClass().getSimpleName());
    }
}
