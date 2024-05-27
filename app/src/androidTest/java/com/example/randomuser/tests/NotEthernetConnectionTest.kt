package com.example.randomuser.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.BaseTestCase
import com.example.randomuser.screen.MainScreen
import com.example.tools.annotation.TestCase
import com.kaspersky.components.kautomator.component.common.views.UiView
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotEthernetConnectionTest : BaseTestCase() {

    @Test
    @TestCase(
        name = "Users list not ethernet connection test",
        description = "Check error shows if ethernet connection absent"
    )
    fun networkErrorTest() {
        run {
            step("Check network error") {
                MainScreen {
                    swipeDownUsersList()
                    checkConnectionErrorMessageDisplayed()
                }
            }
        }
    }
}