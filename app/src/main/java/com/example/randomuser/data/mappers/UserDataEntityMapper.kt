package com.example.randomuser.data.mappers

import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.data.network.models.Coordinates
import com.example.randomuser.data.network.models.Dob
import com.example.randomuser.data.network.models.Id
import com.example.randomuser.data.network.models.Location
import com.example.randomuser.data.network.models.Login
import com.example.randomuser.data.network.models.Name
import com.example.randomuser.data.network.models.Picture
import com.example.randomuser.data.network.models.Registered
import com.example.randomuser.data.network.models.Street
import com.example.randomuser.data.network.models.Timezone
import com.example.randomuser.data.network.models.UserData
import java.text.SimpleDateFormat
import java.util.Locale

fun UserData.toUserEntity(): UserEntity {
    return UserEntity(
        primaryId = 0,
        gender = gender,
        titleName = name?.title,
        firstName = name?.first,
        lastName = name?.last,
        streetName = location?.street?.name,
        streetNumber = location?.street?.number,
        city = location?.city,
        state = location?.state,
        country = location?.country,
        postcode = location?.postcode,
        latitude = location?.coordinates?.latitude,
        longitude = location?.coordinates?.latitude,
        timezoneOffset = location?.timezone?.offset,
        timezoneDescription = location?.timezone?.description,
        email = email,
        uuid = login?.uuid,
        username = login?.username,
        password = login?.password,
        salt = login?.salt,
        md5 = login?.md5,
        sha1 = login?.sha1,
        sha256 = login?.sha256,
        birthdate = dob?.date?.let {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(dob.date)
        },
        age = dob?.age,
        registerDate = registered?.date?.let {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(registered.date)
        },
        registerAge = registered?.age,
        phone = phone,
        cell = cell,
        idName = id?.name,
        idValue = id?.value,
        pictureThumbnail = picture?.thumbnail,
        pictureMedium = picture?.medium,
        pictureLarge = picture?.large,
        nat = nat
    )
}

fun UserEntity.toUserData(): UserData {
    return UserData(
        gender = gender,
        name = Name(
            title = titleName,
            first = firstName,
            last = lastName
        ),
        location = Location(
            street = Street(
                name = streetName,
                number = streetNumber,
            ),
            city = city,
            state = state,
            country = country,
            postcode = postcode,
            coordinates = Coordinates(
                latitude = latitude,
                longitude = longitude,
            ),
            timezone = Timezone(
                offset = timezoneOffset,
                description = timezoneDescription,
            )
        ),
        email = email,
        login = Login(
            uuid = uuid,
            username = username,
            password = password,
            salt = salt,
            md5 = md5,
            sha1 = sha1,
            sha256 = sha256,
        ),
        dob = Dob(
            date = birthdate.toString(),
            age = age,
        ),
        registered = Registered(
            date = registerDate.toString(),
            age = registerAge,
        ),
        phone = phone,
        cell = cell,
        id = Id(
            name = idName,
            value = idValue,
        ),
        picture = Picture(
            thumbnail = pictureThumbnail,
            medium = pictureMedium,
            large = pictureLarge,
        ),
        nat = nat
    )
}
