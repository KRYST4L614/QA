package org.example.tests;

import org.example.core.steps.CreateNewChatSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewChatTest extends BaseTest {
    private final CreateNewChatSteps steps = new CreateNewChatSteps();

    @BeforeEach
    public void setup() {
        steps.prepareTest();
    }

    @AfterEach
    public void cleanup() {
        steps.cleanUpTest();
    }


    @Test
    public void testCanCreateNewChat() {
        String expectedChatName = "New chat";
        steps.createNewChat(expectedChatName);
        assertEquals(steps.getMessagePage().getChatItemWrappers().get(0).getConversationName(), expectedChatName);
    }
}
