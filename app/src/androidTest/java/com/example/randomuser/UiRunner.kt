package com.example.randomuser

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

open class UiRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}