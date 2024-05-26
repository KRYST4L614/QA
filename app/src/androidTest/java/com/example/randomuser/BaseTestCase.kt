package com.example.randomuser

import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase

abstract class BaseTestCase(
    kaspressoBuilder: Kaspresso.Builder = getBuilder()
) : BaseTestCase<Unit, Unit>(
    kaspressoBuilder = kaspressoBuilder,
    dataProducer = { action -> action?.invoke(Unit) }
) {

    private companion object {
        const val FLAKY_SAFETY_TIMEOUT = 5000L

        fun getBuilder(): Kaspresso.Builder =
            Kaspresso.Builder.simple().apply {
                @Suppress("DEPRECATION")
                flakySafetyParams.timeoutMs = FLAKY_SAFETY_TIMEOUT
            }
    }
}