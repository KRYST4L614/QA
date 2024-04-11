package org.example.core.steps;

import org.example.core.pages.FeedPage;
import org.example.core.pages.LoginPage;
import org.example.core.pages.NewPostPage;

public class CreateNewPostSteps {
    private FeedPage feedPage;

    public FeedPage getFeedPage() {
        return feedPage;
    }

    public CreateNewPostSteps prepareTest() {
        LoginPage loginPage = new LoginPage();
        loginPage
                .enterLogin(LoginPage.user.login())
                .enterPassword(LoginPage.user.password())
                .clickLoginButton();
        feedPage = new FeedPage();
        return this;
    }

    public CreateNewPostSteps createNewPost(String text) {
        feedPage = new FeedPage().clickOnPublicationButton().clickOnPublicationRecordButton();
        new NewPostPage().writeInPostTextArea(text).clickOnSubmitButton();
        return this;
    }

    public void cleanUpTest() {
        feedPage.clickOnActionButtomOnCreatedPost().clickOnDeletePostButton();
    }
}
