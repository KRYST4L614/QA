package com.example.randomuser.di

import android.content.Context
import com.example.randomuser.RandomUserApplication
import com.example.randomuser.ui.fragments.MainFragment
import com.example.randomuser.ui.fragments.UserInfoFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(randomUserApplication: RandomUserApplication)
    fun inject(mainFragment: MainFragment)
    fun inject(userInfoFragment: UserInfoFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent

    }

}

@Module(includes = [NetworkModule::class])
class AppModule
