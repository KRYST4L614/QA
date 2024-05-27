package com.example.randomuser.screen

import com.example.randomuser.R
import com.example.randomuser.data.network.models.UserData
import com.example.randomuser.ui.fragments.UserInfoFragment
import com.example.randomuser.util.getBirthdate
import com.example.randomuser.util.getCellPhone
import com.example.randomuser.util.getCity
import com.example.randomuser.util.getEmail
import com.example.randomuser.util.getMobPhone
import com.example.randomuser.util.getName
import com.example.randomuser.util.getPostcode
import com.example.randomuser.util.getRegisteredDate
import com.example.randomuser.util.getTimezone
import com.example.randomuser.util.getUserName
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.utilities.getResourceString
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object UserInfoScreen : KScreen<UserInfoScreen>() {
    override val layoutId = R.layout.user_info_fragment
    override val viewClass = UserInfoFragment::class.java


    val avatar = KTextView { withId(R.id.avatar) }
    val name = KTextView { withId(R.id.name) }
    val callButton = KButton { withId(R.id.call_button) }
    val messageButton = KButton { withId(R.id.message_button) }
    val locationButton = KButton { withId(R.id.location_button) }
    val contactsTitle = KTextView { withId(R.id.contacts) }
    val cellPhone = KTextView { withId(R.id.cell_phone_number) }
    val mobPhone = KTextView { withId(R.id.phone_number) }
    val email = KTextView { withId(R.id.email) }
    val postcode = KTextView { withId(R.id.postcode) }
    val personalInfoTitle = KTextView { withId(R.id.person_info_title) }
    val userName = KTextView { withId(R.id.username) }
    val birthdate = KTextView { withId(R.id.birthdate) }
    val city = KTextView { withId(R.id.city) }
    val registeredDate = KTextView { withId(R.id.registered_date) }
    val timezone = KTextView { withId(R.id.timezone) }

    fun avatarIsDisplayed() = avatar.isDisplayed()

    fun checkCallButtonText(expectedText: String) = checkButtonText(callButton, expectedText)

    fun checkMessageButtonText(expectedText: String) = checkButtonText(messageButton, expectedText)

    fun checkLocationButtonText(expectedText: String) =
        checkButtonText(locationButton, expectedText)

    fun checkPersonalInfoTitleText(expectedTitle: String) =
        checkText(personalInfoTitle, expectedTitle)


    fun checkBirthdateText(expectedBirthdate: String) = checkText(birthdate, expectedBirthdate)

    fun checkCity(expectedCity: String) = checkText(city, expectedCity)

    fun checkRegisteredDateText(expectedDate: String) = checkText(registeredDate, expectedDate)

    fun checkUserName(expectedUserName: String) = checkText(userName, expectedUserName)

    fun checkTimezone(expectedTimezone: String) = checkText(timezone, expectedTimezone)

    fun checkContactTitleText(expectedContactsTitle: String) =
        checkText(contactsTitle, expectedContactsTitle)

    fun checkCellPhoneText(expectedCellPhone: String) = checkText(cellPhone, expectedCellPhone)

    fun checkMobPhoneText(expectedPhone: String) = checkText(mobPhone, expectedPhone)

    fun checkEmailText(expectedEmail: String) = checkText(email, expectedEmail)

    fun checkPostcode(expectedPostcode: String) = checkText(postcode, expectedPostcode)

    fun checkNameText(expectedName: String) = checkText(name, expectedName)

    fun checkScreenWithUser(userData: UserData) {
        avatarIsDisplayed()
        checkMessageButtonText(getResourceString(R.string.message))
        checkCallButtonText("")
        checkLocationButtonText("")
        with(userData) {
            checkNameText(getName())
            checkPersonalInfoTitleText(getResourceString(R.string.personal_data))
            checkUserName(getResourceString(R.string.username).format(getUserName()))
            checkBirthdateText(getResourceString(R.string.birthdate).format(getBirthdate()))
            checkCity(getResourceString(R.string.city).format(getCity()))
            checkRegisteredDateText(
                getResourceString(R.string.registration_date).format(
                    getRegisteredDate()
                )
            )
            checkTimezone(getResourceString(R.string.timezone).format(getTimezone()))
            checkContactTitleText(getResourceString(R.string.contacts))
            checkCellPhoneText(getResourceString(R.string.cell_phone_number).format(getCellPhone()))
            checkMobPhoneText(getResourceString(R.string.phone_number).format(getMobPhone()))
            checkEmailText(getResourceString(R.string.email).format(getEmail()))
            checkPostcode(getResourceString(R.string.postcode).format(getPostcode()))
        }
    }

    private fun checkText(view: KTextView, expectedText: String) {
        with(view) {
            isDisplayed()
            hasText(expectedText)
        }
    }

    private fun checkButtonText(view: KButton, expectedText: String) {
        with(view) {
            isDisplayed()
            hasText(expectedText)
        }
    }

    private fun clickButton(view: KButton) {
        with(view) {
            isDisplayed()
            isClickable()
            click()
        }
    }
}
