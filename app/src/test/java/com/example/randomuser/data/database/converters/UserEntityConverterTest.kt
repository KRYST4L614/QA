package com.example.randomuser.data.database.converters

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.Instant
import java.util.Date
import java.util.stream.Stream

class UserEntityConverterTest {

    companion object {

        @JvmStatic
        fun provideDateAndTime(): Stream<Arguments> = Stream.of(
            Arguments.of(
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")),
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")).time
            ),
            Arguments.of(
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")),
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")).time
            ),
        )
    }

    @ParameterizedTest(name = "{index}. date: {0}, millisSinceEpoch: {1}")
    @MethodSource("provideDateAndTime")
    fun `WHEN convert from date EXPECT milliseconds`(date: Date?, millisSinceEpoch: Long?) {
        assertEquals(UserEntityConverter().fromDate(date), millisSinceEpoch)
    }

    @ParameterizedTest(name = "{index}. date: {0}, millisSinceEpoch: {1}")
    @MethodSource("provideDateAndTime")
    fun `WHEN convert from milliseconds EXPECT date`(date: Date?, millisSinceEpoch: Long?) {
        assertEquals(UserEntityConverter().toDate(millisSinceEpoch), date)
    }

    @Test
    fun `WHEN convert null value EXPECT null result in fromDate method`() {
        assertEquals(UserEntityConverter().fromDate(null), null)
    }

    @Test
    fun `WHEN convert null value EXPECT null result in toDate method`() {
        assertEquals(UserEntityConverter().toDate(null), null)
    }

    //Не смог придумать что можно проверить в проекте на исключения, (мысли были проверить Room или
    // Retrofit но как их тестить не дошло), поэтому написал искуственный метод.
    @Test
    fun `WHEN convert nagative millisSinceEpoch EXPECT IllegalArgumentException`() {
        val exception = assertThrows(IllegalStateException::class.java) {
            UserEntityConverter().toDateWithException(-1)
        }

        assertEquals("MillisSinceEpoch = -1 must be > 0", exception.localizedMessage)
    }
}