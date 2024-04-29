package com.example.randomuser

import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerTestAppComponent

class TestApplication : RandomUserApplication() {
    override fun buildAppComponent(): AppComponent {
        return DaggerTestAppComponent.builder().context(this).build()
    }
}