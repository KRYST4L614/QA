package com.example.randomuser.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.randomuser.data.database.RandomUsersDatabase
import com.example.randomuser.data.database.dao.RandomUserDao
import com.example.randomuser.data.database.models.UserEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.text.SimpleDateFormat
import java.util.Locale

class RepositoryRandomUserDbImplTest {
    val database: RandomUsersDatabase = mock()
    val dao: RandomUserDao = mock()
    val testUserEntity = UserEntity(
        primaryId = 1,
        gender = "female",
        titleName = "Mrs",
        firstName = "Hermelinda",
        lastName = "Paredes",
        streetName = "Viaducto Cuba",
        streetNumber = 6140,
        city = "Nuevo Progreso",
        state = "Oaxaca",
        country = "Mexico",
        postcode = "54915",
        latitude = "28.0653",
        longitude = "149.9763",
        timezoneOffset = "+8:00",
        timezoneDescription = "Beijing, Perth, Singapore, Hong Kong",
        email = "hermelinda.paredes@example.com",
        uuid = "7a5d9f89-7558-412f-b65a-6e06eb3e5b20",
        username = "purplekoala898",
        password = "factory",
        salt = "uwGhj0lA",
        md5 = "ad536443c0a011ea48d1a128aaca51f5",
        sha1 = "74fb69082c70d443e944c5be6a237ee7a2792ed2",
        sha256 = "37904205a76fea3a5b9ad82260621268fc9b810215ed2e72147896551e067396",
        birthdate = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.US
        ).parse("1944-11-25T15:59:16.436Z"),
        age = 79,
        registerDate = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.US
        ).parse("2009-12-16T04:08:55.934Z"),
        registerAge = 14,
        phone = "(672) 936 1080",
        cell = "(682) 991 6630",
        idName = "NSS",
        idValue = "91 94 57 7804 3",
        pictureLarge = "https://randomuser.me/api/portraits/women/86.jpg",
        pictureMedium = "https://randomuser.me/api/portraits/med/women/86.jpg",
        pictureThumbnail = "https://randomuser.me/api/portraits/thumb/women/86.jpg",
        nat = "MX"
    )

    @Test
    fun `WHEN get user invoked EXPECT result tu be same in the database`() {
        whenever(database.dao).thenReturn(dao)
        whenever(database.dao.getUserById(any())).thenReturn(MutableLiveData(testUserEntity))
        RepositoryRandomUserDbImpl.initialize(database)
        assertEquals(
            testUserEntity,
            RepositoryRandomUserDbImpl.get().getUserById(1).value
        )

    }
}