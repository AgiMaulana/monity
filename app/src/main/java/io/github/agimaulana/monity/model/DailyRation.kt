package io.github.agimaulana.monity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import io.github.agimaulana.monity.converter.TimestampConverter
import io.github.agimaulana.monity.LocaleUtils
import java.util.*

@Entity
@TypeConverters(TimestampConverter::class)
data class DailyRation(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val amount: Long,
    val createdAt: Date,
    val deletedAt: Date?
) {
    constructor(amount: Long): this(0, amount, LocaleUtils.localCalendar().time, null)
}