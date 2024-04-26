package com.example.randomuser

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.screen.MainScreen
import com.example.randomuser.screen.UserInfoScreen
import com.example.randomuser.ui.activities.MainActivity
import com.example.tools.annotation.TestCase
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UsersListTest : KTestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @TestCase(
        name = "Test-1",
        description = "Check that users list correctly visible at least 5 items"
    )
    fun checkUsersListDisplaysItems() {
        before {
        }.after {

        }.run {
            step("Check that items visible") {
                MainScreen {
                    usersList.isDisplayed()
                    device.uiDevice.waitForIdle()
                    assertTrue(usersList.getSize() >= 5)
                    for (i in 0..<5) {
                        usersList {
                            childAt<MainScreen.UserItem>(i) {
                                avatar.isDisplayed()
                                name.isDisplayed()
                                info.isDisplayed()
                                callButton.isDisplayed()
                                messageButton.isDisplayed()
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @TestCase(
        name = "Test-2",
        description = "Check that the list item is clickable and move to another screen"
    )
    fun checkClickOnItemUsersList() {
        before {
        }.after {

        }.run {
            step("Click on the item") {
                MainScreen {
                    usersList.isDisplayed()
                    device.uiDevice.waitForIdle()
                    assertTrue(usersList.getSize() >= 1)
                    usersList {
                        firstChild<MainScreen.UserItem> {
                            isDisplayed()
                            isClickable()
                            click()
                        }
                    }
                }
            }
            step("Check user info") {
                UserInfoScreen {
                    avatar.isDisplayed()
                    name.isDisplayed()
                    city.isDisplayed()
                    messageButton.isDisplayed()
                    callButton.isDisplayed()
                    locationButton.isDisplayed()
                    cellPhoneNumber.isDisplayed()
                    phoneNumber.isDisplayed()
                }
            }
        }
    }
}