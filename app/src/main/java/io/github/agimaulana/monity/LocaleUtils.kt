package io.github.agimaulana.monity

import java.text.SimpleDateFormat
import java.util.*

object LocaleUtils {
    val timeZone: TimeZone get() = TimeZone.getTimeZone("Asia/Jakarta")
    val locale: Locale get() = Locale("id", "ID")

    fun localCalendar(
        timeZone: TimeZone = this.timeZone,
        locale: Locale = this.locale
    ): Calendar = Calendar.getInstance(timeZone, locale)

    fun dateFormat(format: String) = SimpleDateFormat(format, locale)
}