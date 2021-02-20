package io.github.agimaulana.monity.extensions

import java.util.*

class Calendars {
    val timeZone: TimeZone get() = TimeZone.getTimeZone("Asia/Jakarta")
    val locale: Locale get() = Locale("id", "ID")

    companion object {
        private val calendars = Calendars()

        fun localCalendar(
            timeZone: TimeZone = calendars.timeZone,
            locale: Locale = calendars.locale
        ): Calendar = Calendar.getInstance(timeZone, locale)
    }
}