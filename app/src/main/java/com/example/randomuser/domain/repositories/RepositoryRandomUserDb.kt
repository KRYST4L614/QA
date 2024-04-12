package com.example.randomuser.domain.repositories

import androidx.lifecycle.LiveData
import com.example.randomuser.data.database.models.UserEntity

interface RepositoryRandomUserDb {
    fun getUserById(primaryId: Int): LiveData<UserEntity>
}