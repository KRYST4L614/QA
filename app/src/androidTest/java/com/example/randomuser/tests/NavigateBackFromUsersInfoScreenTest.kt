package com.example.randomuser.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.BaseTestCase
import com.example.randomuser.data.network.models.UsersListData
import com.example.randomuser.mockServer.MockRequest
import com.example.randomuser.mockServer.MockRequestDispatcher
import com.example.randomuser.mockServer.MockServerRule
import com.example.randomuser.screen.MainScreen
import com.example.randomuser.util.getLocalJsonBody
import com.example.tools.annotation.TestCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigateBackFromUsersInfoScreenTest : BaseTestCase() {

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

    private val users: UsersListData? = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(UsersListData::class.java)
        .fromJson(getLocalJsonBody<UsersListItemClickableTest>("MockUsersList.json"))

    @Test
    @TestCase(
        name = "Navigate back from users info screen test",
        description = "Check if press back from user info screen opens main screen"
    )
    fun navigateBackFromUsersInfoScreenTest() {
        before {
            MainScreen {
                checkUsers(users!!.userItems)
                getUsersListItem(0) {
                    isClickable()
                    click()
                }
            }
        }.after {
        }.run {
            step("Check network error") {
                device.uiDevice.pressBack()
                MainScreen {
                    checkUsers(users!!.userItems)
                }
            }
        }
    }
}