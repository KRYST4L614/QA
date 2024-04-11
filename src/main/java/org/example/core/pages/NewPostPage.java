package org.example.core.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static org.example.utils.Utils.checkElementState;

public class NewPostPage extends BasePage {
    private final static By postTextArea = By.xpath("//div[@data-module='postingForm/mediaText']");
    private final static By submitButton = By.xpath("//button[@data-l='t,button.submit']");

    public NewPostPage writeInPostTextArea(String text) {
        checkElementState(postTextArea, "Post text area", visible, this.getClass().getSimpleName())
                .setValue(text);
        return this;
    }

    public FeedPage clickOnSubmitButton() {
        checkElementState(submitButton, "Submit button", visible, this.getClass().getSimpleName())
                .click();
        return new FeedPage();
    }

    @Override
    public void checkPage() {
        checkElementState(postTextArea, "Post text area", visible, this.getClass().getSimpleName());
        checkElementState(submitButton, "Submit button", visible, this.getClass().getSimpleName());
    }
}
