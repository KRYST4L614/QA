package com.example.randomuser.data.repositories.network

import com.example.randomuser.data.network.models.UsersListData
import retrofit2.Response

interface RepositoryRandomUserApi {
    suspend fun fetchUsersPage(page: Int, results: Int = 15): Response<UsersListData>
}