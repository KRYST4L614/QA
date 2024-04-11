package org.example.core.elements;

import org.example.core.pages.LoadablePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.example.utils.Utils.checkElementState;

public class MessageToolBarElement implements LoadablePage {
    private final static By chatInfoButton = By.xpath(".//*[@data-tsid='chat_info_button']");
    private final static By deleteChatButton = By.xpath(".//*[@data-tsid='remove-dialog-btn']");
    private final static By confirmDeleteChatButton = By.xpath(".//*[@data-tsid='confirm-primary']");

    public MessageToolBarElement() {
        checkPage();
    }

    public MessageToolBarElement clickOnChatInfoButton() {
        $(chatInfoButton).shouldBe(visible.because("Chat info button should be visible")).click();
        return this;
    }

    public MessageToolBarElement clickOnDeleteChatButton() {
        checkElementState(deleteChatButton, "Delete chat button", visible, this.getClass().getSimpleName()).click();
        return this;
    }

    public MessageToolBarElement clickOnConfirmDeleteButton() {
        checkElementState(confirmDeleteChatButton, "Confirm delete chat button", visible, this.getClass().getSimpleName()).click();
        return this;
    }

    @Override
    public void checkPage() {
        checkElementState(chatInfoButton, "Chat info button", visible, this.getClass().getSimpleName());
    }
}
