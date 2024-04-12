package com.example.randomuser.data.repositories

import com.example.randomuser.data.network.api.RandomUserApi
import com.example.randomuser.data.network.models.UsersListData
import com.example.randomuser.domain.repositories.RepositoryRandomUserApi
import retrofit2.Response
import retrofit2.awaitResponse

class RepositoryRandomUserApiImpl(
    private val api: RandomUserApi
) : RepositoryRandomUserApi {

    override suspend fun fetchUsersPage(page: Int, results: Int): Response<UsersListData> {
        return api.getUsersPage(page, results).awaitResponse()
    }

    companion object {
        private var INSTANCE: RepositoryRandomUserApiImpl? = null

        fun initialize(
            api: RandomUserApi
        ) {
            if (INSTANCE == null) {
                INSTANCE = RepositoryRandomUserApiImpl(api)
            }
        }

        fun get(): RepositoryRandomUserApiImpl {
            return INSTANCE
                ?: throw IllegalStateException("${this::class::java.name} must be initialized")
        }
    }
}