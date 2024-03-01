package com.example.randomuser.data.repositories.db

import androidx.lifecycle.LiveData
import com.example.randomuser.data.database.models.UserEntity

interface RepositoryRandomUserDb {
    fun getUserById(primaryId: Int): LiveData<UserEntity>
}