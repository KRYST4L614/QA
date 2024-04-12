package com.example.randomuser.domain.repositories

import com.example.randomuser.data.network.models.UsersListData
import retrofit2.Response

interface RepositoryRandomUserApi {
    suspend fun fetchUsersPage(page: Int, results: Int = 15): Response<UsersListData>
}