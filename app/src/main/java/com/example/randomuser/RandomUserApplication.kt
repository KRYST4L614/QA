package com.example.randomuser

import android.app.Application
import android.content.Context
import com.example.randomuser.data.database.RandomUsersDatabase
import com.example.randomuser.data.network.api.RandomUserApi
import com.example.randomuser.data.repositories.RepositoryRandomUserDbImpl
import com.example.randomuser.data.repositories.RepositoryRandomUserApiImpl
import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerAppComponent
import javax.inject.Inject

class RandomUserApplication : Application() {
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var randomUserApi: RandomUserApi

    @Inject
    lateinit var randomUsersDatabase: RandomUsersDatabase

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
        appComponent.inject(this)
        initializeRepositories(randomUsersDatabase, randomUserApi)
    }

    @Inject
    fun initializeRepositories(
        database: RandomUsersDatabase,
        randomUserApi: RandomUserApi
    ) {
        RepositoryRandomUserDbImpl.initialize(database)
        RepositoryRandomUserApiImpl.initialize(randomUserApi)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is RandomUserApplication -> this.appComponent
        else -> this.applicationContext.appComponent
    }