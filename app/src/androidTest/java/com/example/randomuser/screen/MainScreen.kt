package com.example.randomuser.screen

import android.view.View
import com.example.randomuser.R
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object MainScreen : Screen<MainScreen>() {
    val usersList = KRecyclerView(
        builder = { withId(R.id.user_recycler_view) },
        itemTypeBuilder = { itemType(::UserItem) }
    )

    class UserItem(parent: Matcher<View>) : KRecyclerItem<UserItem>(parent) {
        val avatar = KTextView(parent) { withId(R.id.avatar) }
        val name = KTextView(parent) { withId(R.id.name) }
        val info = KTextView(parent) { withId(R.id.info) }
        val callButton = KButton(parent) { withId(R.id.call_button) }
        val messageButton = KButton(parent) { withId(R.id.message_button) }
    }

}