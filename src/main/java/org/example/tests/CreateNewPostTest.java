package org.example.tests;

import org.example.core.steps.CreateNewPostSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewPostTest extends BaseTest {
    private final CreateNewPostSteps steps = new CreateNewPostSteps();

    @BeforeEach
    public void setup() {
        steps.prepareTest();
    }

    @AfterEach
    public void cleanup() {
        steps.cleanUpTest();
    }

    @Test
    public void testCanMakeNewPost() {
        String expectedPostText = "Hey";
        steps.createNewPost(expectedPostText);
        assertEquals(steps.getFeedPage().getTextCreatedPost(), expectedPostText);
    }
}
