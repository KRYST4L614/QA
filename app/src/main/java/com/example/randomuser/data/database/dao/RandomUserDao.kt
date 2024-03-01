package com.example.randomuser.data.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.randomuser.data.database.models.UserEntity

@Dao
interface RandomUserDao {
    @Upsert
    fun upsertAll(users: List<UserEntity>)

    @Query("SELECT * FROM randomUsers")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM randomUsers")
    fun clearAll()

    @Query("SELECT * FROM randomUsers WHERE primaryId = (:primaryId)")
    fun getUserById(primaryId: Int): LiveData<UserEntity>
}