package com.example.randomuser.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.BaseTestCase
import com.example.randomuser.screen.MainScreen
import com.example.randomuser.ui.activities.MainActivity
import com.example.tools.annotation.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotEthernetConnectionTest : BaseTestCase() {


    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    @TestCase(
        name = "Users list not ethernet connection test",
        description = "Check error shows if ethernet connection absent"
    )
    fun checkNetworkError() {
        run {
            step("Check network error") {
                MainScreen {
                    swipeDownUsersList()
                    checkConnectionErrorImageDisplayed()
                    checkConnectionErrorMessageDisplayed()
                }
            }
        }
    }
}