package com.example.randomuser.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersListData(
    @Json(name = "results")
    var userItems: List<UserData>
)
