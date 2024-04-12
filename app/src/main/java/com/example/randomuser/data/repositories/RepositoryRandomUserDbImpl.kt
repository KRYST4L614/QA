package com.example.randomuser.data.repositories

import androidx.lifecycle.LiveData
import com.example.randomuser.data.database.RandomUsersDatabase
import com.example.randomuser.data.database.models.UserEntity
import com.example.randomuser.domain.repositories.RepositoryRandomUserDb

class RepositoryRandomUserDbImpl(
    private val database: RandomUsersDatabase
) : RepositoryRandomUserDb {
    override fun getUserById(primaryId: Int): LiveData<UserEntity> =
        database.dao.getUserById(primaryId)

    companion object {
        private var INSTANCE: RepositoryRandomUserDbImpl? = null

        fun initialize(
            database: RandomUsersDatabase
        ) {
            if (INSTANCE == null) {
                INSTANCE = RepositoryRandomUserDbImpl(database)
            }
        }

        fun get(): RepositoryRandomUserDbImpl {
            return INSTANCE
                ?: throw IllegalStateException("${this::class::java.name} must be initialized")
        }
    }
}