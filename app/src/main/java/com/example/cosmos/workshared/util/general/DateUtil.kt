package com.example.cosmos.workshared.util.general

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val APOD_DATE_FORMAT = "yyyy-MM-dd"

fun getCalendarFromDateOrCurrent(date: String?): Calendar {
    val calendar: Calendar = Calendar.getInstance()
    if (!date.isNullOrBlank()) {
        val format: DateFormat = SimpleDateFormat(APOD_DATE_FORMAT, Locale.getDefault())
        calendar.time = format.parse(date)
    }
    return calendar
}

fun getAPODFormattedDate(
    year: Int,
    month: Int,
    day: Int
): String {
    return year.toString() + "-" + (month + 1) + "-" + day
}
