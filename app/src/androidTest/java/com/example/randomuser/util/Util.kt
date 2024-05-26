package com.example.randomuser.util

import android.content.res.Resources
import android.text.format.DateFormat
import com.example.randomuser.data.network.models.UserData
import java.text.SimpleDateFormat
import java.util.Locale

inline fun <reified T> getLocalJsonBody(relativePath: String): String {
    val resource = T::class.java.classLoader
        ?.getResourceAsStream(relativePath)
        ?: throw Resources.NotFoundException(
            "Unable to find '$relativePath'"
        )

    return String(resource.readBytes())
}

internal fun UserData.getName(): String {
    return listOf(
        this.name!!.title,
        this.name!!.first,
        this.name!!.last
    ).joinToString(" ")
}

internal fun UserData.getUserName(): String {
    return this.login!!.username!!
}
internal fun UserData.getCity(): String {
    return listOf(
        this.location!!.city,
        this.location!!.country,
    ).joinToString(", ")
}

internal fun UserData.getBirthdate(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(dob!!.date!!)
    return DateFormat.format("d MMMM yyyy", date!!.time).toString()
}

internal fun UserData.getRegisteredDate(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(registered!!.date!!)
    return DateFormat.format("d MMMM yyyy", date!!.time).toString()
}

internal fun UserData.getTimezone(): String {
    return this.location!!.timezone!!.offset.toString()
}

internal fun UserData.getCellPhone(): String {
    return this.cell!!.toString()
}

internal fun UserData.getMobPhone(): String {
    return this.phone!!.toString()
}

internal fun UserData.getEmail(): String {
    return this.email.toString()
}

internal fun UserData.getPostcode(): String {
    return this.location!!.postcode.toString()
}