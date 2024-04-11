package org.example.core.pages;

import org.example.core.elements.SideNavigationBarElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static org.example.utils.Utils.checkElementState;

public class FeedPage extends BasePage {
    private final SideNavigationBarElement sideBar = new SideNavigationBarElement();
    private static final By publicationButton = By.xpath("//*[@data-l='t,pf_dropdown']");
    private static final By publicationRecordButton = By.xpath("//*[@data-l='t,feed.posting.ui.input']");
    private static final By actionButtonCreatedPost = By.xpath("//*[@class='feed-action']");
    private static final By deletePostButton = By.xpath("//*[@id='hook_Block_ShortcutMenu']" +
            "//*[contains(@hrefattrs, 'StatusLayer_deleteButton')]");
    private static final By newFeedText = By.xpath("//*[@class='feed_b']");

    public FeedPage() {
        checkPage();
    }

    public String getNavSideBarUserName() {
        return sideBar.getNavSideBarUserName();
    }

    public FeedPage clickOnPublicationButton() {
        checkElementState(publicationButton, "Publication button", visible, this.getClass().getSimpleName())
                .click();
        return this;
    }

    public FeedPage clickOnPublicationRecordButton() {
        checkElementState(publicationRecordButton, "Publication record button", visible, this.getClass().getSimpleName())
                .click();
        return this;
    }

    public String getTextCreatedPost() {
        return checkElementState(newFeedText, "New feed text", visible, this.getClass().getSimpleName())
                .getText();
    }


    @Override
    public void checkPage() {
        checkElementState(publicationButton, "Publication button", visible, this.getClass().getSimpleName());
    }

    public FeedPage clickOnActionButtomOnCreatedPost() {
        checkElementState(actionButtonCreatedPost, "Action button on created post", visible, this.getClass().getSimpleName()).click();
        return this;
    }

    public FeedPage clickOnDeletePostButton() {
        checkElementState(deletePostButton, "Delete button on created post", visible, this.getClass().getSimpleName()).click();
        return this;
    }
}
