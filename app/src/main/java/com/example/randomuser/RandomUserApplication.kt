package com.example.randomuser

import android.app.Application
import android.content.Context
import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerAppComponent

open class RandomUserApplication : Application() {
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = buildAppComponent()
    }

    open fun buildAppComponent(): AppComponent {
        return DaggerAppComponent.builder().context(this).build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is RandomUserApplication -> this.appComponent
        else -> this.applicationContext.appComponent
    }