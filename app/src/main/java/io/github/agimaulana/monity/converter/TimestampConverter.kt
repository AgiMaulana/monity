package io.github.agimaulana.monity.converter

import androidx.room.TypeConverter
import java.util.*


class TimestampConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let(::Date)

    @TypeConverter
    fun dateToTimestamp(value: Date?): Long? = value?.time
}