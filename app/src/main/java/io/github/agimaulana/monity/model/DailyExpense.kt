package io.github.agimaulana.monity.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import io.github.agimaulana.monity.LocaleUtils
import io.github.agimaulana.monity.converter.TimestampConverter
import java.util.*

@Entity
@TypeConverters(TimestampConverter::class)
data class DailyExpense(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val activityTime: Date?,
        val activityName: String,
        val amount: Long,
        val updatedAt: Date?,
        val deletedAt: Date?
) {

    constructor(activityTime: Date?, activityName: String, amount: Long):
            this(0, activityTime, activityName, amount, LocaleUtils.localCalendar().time, null)

    @Ignore
    private val numberFormat = LocaleUtils.numberFormat()
    val amountAsCurrency: String get() = numberFormat.format(amount)

    @Ignore
    private val timeFormat = LocaleUtils.createDateFormat("HH:mm")
    val time: String get() = (activityTime ?: LocaleUtils.localCalendar().time).let(timeFormat::format)

}