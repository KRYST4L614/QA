package com.example.randomuser.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.randomuser.data.database.converters.UserEntityConverter
import com.example.randomuser.data.database.dao.RandomUserDao
import com.example.randomuser.data.database.models.UserEntity

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(UserEntityConverter::class)
abstract class RandomUsersDatabase : RoomDatabase() {
    abstract val dao: RandomUserDao
}