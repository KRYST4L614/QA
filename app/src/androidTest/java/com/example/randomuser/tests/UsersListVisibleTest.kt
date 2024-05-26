package com.example.randomuser.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.randomuser.BaseTestCase
import com.example.randomuser.BuildConfig
import com.example.randomuser.data.network.models.UsersListData
import com.example.randomuser.mockServer.MockRequest
import com.example.randomuser.mockServer.MockRequestDispatcher
import com.example.randomuser.mockServer.MockServerRule
import com.example.randomuser.screen.MainScreen
import com.example.randomuser.ui.activities.MainActivity
import com.example.randomuser.util.getLocalJsonBody
import com.example.tools.annotation.TestCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class UsersListVisibleTest : BaseTestCase() {
    @get:Rule(order = 0)
    val mockServerRule = MockServerRule().also {
        MockRequestDispatcher.addMockRequests(
            MockRequest(
                remotePath = "/api/?page=0&results=${BuildConfig.PAGER_PAGE_SIZE}",
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
        name = "Users list visible test",
        description = "Check that users list correctly visible at least 5 items"
    )
    fun checkUsersListDisplaysItems() {
        before {
        }.after {
        }.run {
            step("Check that items visible") {
                MainScreen {
                    flakySafely {
                        assertTrue(getUsersListItemCount() >= 5)
                    }
                    checkUsers(
                        users!!.userItems
                    )
                }
            }
        }
    }

}