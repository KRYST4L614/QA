package com.example.randomuser.di

import android.content.Context
import androidx.room.Room
import com.example.randomuser.BuildConfig
import com.example.randomuser.data.database.RandomUsersDatabase
import com.example.randomuser.data.database.dao.RandomUserDao
import com.example.randomuser.data.repositories.RepositoryRandomUserDbImpl
import com.example.randomuser.domain.repositories.RepositoryRandomUserDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): RandomUsersDatabase {
        return Room.databaseBuilder(
            context,
            RandomUsersDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepositoryRandomUserDb(): RepositoryRandomUserDb {
        return RepositoryRandomUserDbImpl.get()
    }

    @Provides
    fun provideRandomUserDao(database: RandomUsersDatabase): RandomUserDao {
        return database.dao
    }
}