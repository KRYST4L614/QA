package com.example.randomuser

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.randomuser.ui.activities.MainActivity
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase
import org.junit.Rule

abstract class BaseTestCase(
    kaspressoBuilder: Kaspresso.Builder = getBuilder()
) : BaseTestCase<Unit, Unit>(
    kaspressoBuilder = kaspressoBuilder,
    dataProducer = { action -> action?.invoke(Unit) }
) {

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private companion object {
        const val FLAKY_SAFETY_TIMEOUT = 5000L

        fun getBuilder(): Kaspresso.Builder =
            Kaspresso.Builder.simple().apply {
                @Suppress("DEPRECATION")
                flakySafetyParams.timeoutMs = FLAKY_SAFETY_TIMEOUT
            }
    }
}