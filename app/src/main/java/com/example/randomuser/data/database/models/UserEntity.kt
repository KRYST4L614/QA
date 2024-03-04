package com.example.randomuser.data.database.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "randomUsers")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryId: Int,
    val gender: String?,
    val titleName: String?,
    val firstName: String?,
    val lastName: String?,
    val streetName: String?,
    val streetNumber: Int?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val latitude: String?,
    val longitude: String?,
    val timezoneOffset: String?,
    val timezoneDescription: String?,
    val email: String?,
    val uuid: String?,
    val username: String?,
    val password: String?,
    val salt: String?,
    val md5: String?,
    val sha1: String?,
    val sha256: String?,
    val birthdate: Date?,
    val age: Int?,
    val registerDate: Date?,
    val registerAge: Int?,
    val phone: String?,
    val cell: String?,
    val idName: String?,
    val idValue: String?,
    val pictureThumbnail: String?,
    val pictureMedium: String?,
    val pictureLarge: String?,
    val nat: String?,
) : Parcelable
