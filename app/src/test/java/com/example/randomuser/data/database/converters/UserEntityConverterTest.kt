package com.example.randomuser.data.database.converters

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.Instant
import java.util.Date

@RunWith(Parameterized::class)
class UserEntityConverterTest(
    private val date: Date?,
    private val milliseconds: Long?,
) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")),
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")).time
            ),
            arrayOf(
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")),
                Date.from(Instant.parse("2024-04-12T11:10:20.01Z")).time
            ),
        )
    }

    @Test
    fun `WHEN convert from date EXPECT milliseconds`() {
        assertEquals(UserEntityConverter().fromDate(date), milliseconds)
    }

    @Test
    fun `WHEN convert from milliseconds EXPECT date`() {
        assertEquals(UserEntityConverter().toDate(milliseconds), date)
    }

    @Test
    fun `When convert null value EXPECT null result in fromDate method`() {
        assertEquals(UserEntityConverter().fromDate(null), null)
    }

    @Test
    fun `When convert null value EXPECT null result in toDate method`() {
        assertEquals(UserEntityConverter().toDate(null), null)
    }
}