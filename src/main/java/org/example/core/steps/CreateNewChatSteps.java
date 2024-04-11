package org.example.core.steps;

import org.example.core.pages.FeedPage;
import org.example.core.pages.LoginPage;
import org.example.core.pages.MessagePage;

public class CreateNewChatSteps {
    private FeedPage feedPage;
    private MessagePage messagePage;

    public MessagePage getMessagePage() {
        return messagePage;
    }

    public CreateNewChatSteps prepareTest() {
        LoginPage loginPage = new LoginPage();
        loginPage
                .enterLogin(LoginPage.user.login())
                .enterPassword(LoginPage.user.password())
                .clickLoginButton();
        feedPage = new FeedPage();
        return this;
    }

    public CreateNewChatSteps createNewChat(String expectedChatName) {
        messagePage = feedPage.openMessagePage();
        messagePage
                .clickOnCreateButton()
                .clickOnNewMessageMenuButton()
                .writeInChatNameInput(expectedChatName)
                .clickCreateEmptyChatButton();
        return this;
    }

    public void cleanUpTest() {
        messagePage
                .clickOnChatInfoButton()
                .clickOnDeleteChatButton()
                .clickOnConfirmDeleteChatButton();
    }

}
