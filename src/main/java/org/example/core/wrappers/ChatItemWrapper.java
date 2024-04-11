package org.example.core.wrappers;

import com.codeborne.selenide.SelenideElement;
import org.example.core.pages.LoadablePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;

public class ChatItemWrapper implements LoadablePage {
    private final static By conversationName = By.xpath(".//*[@data-tsid='conversation_name']");
    private final SelenideElement item;

    public ChatItemWrapper(SelenideElement root) {
        this.item = root;
    }

    public String getConversationName() {
        return item.$(conversationName).shouldBe(visible.because("Chat name should be visible on chat item")).getText();
    }


    @Override
    public void checkPage() {
        item.$(conversationName).shouldBe(visible.because("Chat name should be visible on chat item"));
    }
}
