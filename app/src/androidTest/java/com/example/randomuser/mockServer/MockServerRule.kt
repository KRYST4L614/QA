package com.example.randomuser.mockServer

import androidx.test.core.app.ApplicationProvider
import com.example.randomuser.RandomUserApplication
import com.example.randomuser.di.DaggerTestAppComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockServerRule : TestRule {
    val mockServer = MockWebServer()
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                mockServer.setDispatcher(MockRequestDispatcher)
                mockServer.start(8080)

                val app = ApplicationProvider.getApplicationContext<RandomUserApplication>()

                val appInjector = DaggerTestAppComponent.builder()
                    .context(app)
                    .build()
                appInjector.inject(app)
                try {
                    base?.evaluate()
                } finally {
                    mockServer.shutdown()
                }
            }

        }
    }
}