package com.example.randomuser.data.network.api

import com.example.randomuser.data.network.models.UsersListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/")
    fun getUsersPage(@Query("page") page: Int, @Query("results") results: Int): Call<UsersListData>
}