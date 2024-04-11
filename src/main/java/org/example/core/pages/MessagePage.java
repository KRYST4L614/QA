package org.example.core.pages;

import org.example.core.elements.MessageToolBarElement;
import org.example.core.wrappers.ChatItemWrapper;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.example.utils.Utils.checkElementState;

public class MessagePage implements LoadablePage {
    private MessageToolBarElement messageToolBar = null;
    private final static By createButton = By.xpath(".//*[@data-l='t, createMenu']");
    private final static By newMsgMenuButton = By.xpath(".//*[@data-tsid='plus_create_chat']");
    private final static By newMsgChatInput = By.xpath(".//*[@data-tsid='chat-theme-input']");
    private final static By createEmptyChatButton = By.xpath(".//*[@data-tsid='finish_create_chat_button']");
    private final static By chatItem = By.xpath(".//*[@data-l='t,chatsListItem']");

    public MessagePage clickOnCreateButton() {
        $(createButton).hover();
        checkElementState(createButton, "Create button", exist, this.getClass().getSimpleName()).click();
        return this;
    }

    public MessagePage clickOnNewMessageMenuButton() {
        checkElementState(newMsgMenuButton, "New message menu button", visible, this.getClass().getSimpleName()).click();
        return this;
    }

    public MessagePage writeInChatNameInput(String chatName) {
        checkElementState(newMsgChatInput, "Chat name input", visible, this.getClass().getSimpleName()).setValue(chatName);
        return this;
    }

    public MessagePage clickCreateEmptyChatButton() {
        checkElementState(createEmptyChatButton, "Create empty chat button", visible, this.getClass().getSimpleName()).click();
        messageToolBar = new MessageToolBarElement();
        return this;
    }

    public List<ChatItemWrapper> getChatItemWrappers() {
        return $$(chatItem).asDynamicIterable().stream().map(
                ChatItemWrapper::new
        ).collect(Collectors.toList());
    }

    public MessagePage clickOnChatInfoButton() {
        messageToolBar.clickOnChatInfoButton();
        return this;
    }

    public MessagePage clickOnDeleteChatButton() {
        messageToolBar.clickOnDeleteChatButton();
        return this;
    }

    public MessagePage clickOnConfirmDeleteChatButton() {
        messageToolBar.clickOnConfirmDeleteButton();
        messageToolBar = null;
        return this;
    }


    @Override
    public void checkPage() {
        checkElementState(createButton, "Create button", visible, this.getClass().getSimpleName());
        checkElementState(newMsgMenuButton, "New message menu button", visible, this.getClass().getSimpleName());
        checkElementState(newMsgChatInput, "New message chat input", visible, this.getClass().getSimpleName());
        checkElementState(createEmptyChatButton, "Create empty chat button", visible, this.getClass().getSimpleName());
    }
}
