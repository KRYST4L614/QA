package com.example.randomuser.screen

import com.example.randomuser.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object UserInfoScreen : Screen<UserInfoScreen>() {
    val avatar = KTextView { withId(R.id.avatar) }
    val name = KTextView { withId(R.id.name) }
    val callButton = KButton { withId(R.id.call_button) }
    val messageButton = KButton { withId(R.id.message_button) }
    val locationButton = KButton { withId(R.id.location_button) }
    val phoneNumber = KTextView { withId(R.id.phone_number) }
    val cellPhoneNumber = KTextView { withId(R.id.cell_phone_number) }
    val city = KTextView { withId(R.id.city) }
}