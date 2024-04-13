package com.example.randomuser.data.database.converters

import androidx.room.TypeConverter
import java.util.Date

class UserEntityConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    fun toDateWithException(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            if (millisSinceEpoch < 0) {
                throw IllegalStateException("MillisSinceEpoch = ${millisSinceEpoch} must be > 0")
            }
            Date(it)
        }
    }
}
