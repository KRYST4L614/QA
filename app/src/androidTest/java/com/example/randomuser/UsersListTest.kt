package com.example.randomuser

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.mockServer.MockRequest
import com.example.randomuser.mockServer.MockRequestDispatcher
import com.example.randomuser.mockServer.MockServerRule
import com.example.randomuser.screen.MainScreen
import com.example.randomuser.screen.UserInfoScreen
import com.example.randomuser.ui.activities.MainActivity
import com.example.tools.annotation.TestCase
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UsersListTest : KTestCase() {

    @get:Rule(order = 0)
    val mockServerRule = MockServerRule().also {
        MockRequestDispatcher.addMockRequests(
            MockRequest(
                remotePath = "/api/?page=0&results=15",
                localPath = "MockUsersList.json",
                requestCode = 200
            ),
        )
    }

    @get:Rule(order = 1)
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
                    flakySafely {
                        assertTrue(usersList.getSize() >= 5)
                    }
                    checkUsers(
                        User("Mrs Begüm Elçiboğa", "Karabük, Turkey"),
                        User("Mr Guterre Fogaça", "Bragança, Brazil"),
                        User("Ms Rose Park", "Stirling, Canada"),
                        User("Mr Javier Núñez", "Barcelona, Spain"),
                        User("Mr Leon Scott", "Newry, United Kingdom")
                    )
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
                    flakySafely {
                        assertTrue(usersList.getSize() >= 1)
                    }
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

    data class User(val name: String, val info: String)

    private fun checkUsers(vararg users: User) {
        users.forEachIndexed { index, user ->
            MainScreen {
                usersList {
                    childAt<MainScreen.UserItem>(index) {
                        avatar.isDisplayed()
                        name.isDisplayed()
                        name.hasText(user.name)
                        info.isDisplayed()
                        info.hasText(user.info)
                        callButton.isDisplayed()
                        messageButton.isDisplayed()
                    }
                }
            }
        }
    }
}