package com.example.randomuser.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MockUrlModule::class, DatabaseModule::class])
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): TestAppComponent
    }

}