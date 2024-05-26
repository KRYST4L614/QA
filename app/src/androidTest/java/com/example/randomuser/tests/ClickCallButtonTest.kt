package com.example.randomuser.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.BaseTestCase
import com.example.randomuser.data.network.models.UsersListData
import com.example.randomuser.mockServer.MockRequest
import com.example.randomuser.mockServer.MockRequestDispatcher
import com.example.randomuser.mockServer.MockServerRule
import com.example.randomuser.screen.MainScreen
import com.example.randomuser.screen.UserInfoScreen
import com.example.randomuser.ui.activities.MainActivity
import com.example.randomuser.util.getLocalJsonBody
import com.example.tools.annotation.TestCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ClickCallButtonTest : BaseTestCase() {

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

    private val users: UsersListData? = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(UsersListData::class.java)
        .fromJson(getLocalJsonBody<UsersListItemClickableTest>("MockUsersList.json"))

    @Test
    @TestCase(
        name = "Users list clickable test",
        description = "Check that the list item is clickable and move to another screen"
    )
    fun checkClickOnItemUsersList() {
        before {
        }.after {
        }.run {
            step("Click on the item") {
                MainScreen {
                    flakySafely {
                        junit.framework.TestCase.assertTrue(getUsersListItemCount() >= 1)
                    }
                    getUsersListItem(0) {
                        isDisplayed()
                        isClickable()
                        click()
                    }
                }
            }
        }
    }
}