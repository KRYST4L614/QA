package com.example.randomuser.screen

import android.view.View
import com.example.randomuser.R
import com.example.randomuser.data.network.models.UserData
import com.example.randomuser.util.getCity
import com.example.randomuser.util.getName
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object MainScreen : Screen<MainScreen>() {
    private val usersList = KRecyclerView(
        builder = { withId(R.id.user_recycler_view) },
        itemTypeBuilder = { itemType(::UserItem) }
    )

    private val connectionErrorImage = KImageView { withId(R.id.cloud_off) }
    private val connectionErrorMessage = KTextView { withId(R.id.error_message) }

    class UserItem(parent: Matcher<View>) : KRecyclerItem<UserItem>(parent) {
        val avatar = KTextView(parent) { withId(R.id.avatar) }
        val name = KTextView(parent) { withId(R.id.name) }
        val info = KTextView(parent) { withId(R.id.info) }
        val callButton = KButton(parent) { withId(R.id.call_button) }
        val messageButton = KButton(parent) { withId(R.id.message_button) }
    }

    fun getUsersListItemCount() = usersList.getSize()
    fun swipeDownUsersList() = usersList.swipeDown()

    fun getUsersListItem(position: Int, func: UserItem.() -> Unit) {
        usersList.isDisplayed()
        usersList.childAt<UserItem>(position, func)
    }

    fun checkUsers(users: List<UserData>) {
        users.forEachIndexed { index, user ->
            usersList {
                childAt<UserItem>(index) {
                    avatar.isDisplayed()
                    name.isDisplayed()
                    name.hasText(user.getName())
                    info.isDisplayed()
                    info.hasText(user.getCity())
                    callButton.isDisplayed()
                    callButton.isClickable()
                    messageButton.isDisplayed()
                    messageButton.isClickable()
                }
            }
        }
    }

    fun checkConnectionErrorImageDisplayed() = connectionErrorImage.isDisplayed()
    fun checkConnectionErrorMessageDisplayed() = connectionErrorMessage.isDisplayed()
}