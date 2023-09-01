package com.example.weathernow.util

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

const val DAY_NAME_IN_WEEK_PATTERN = "EEEE"
const val DAY_DATE_PATTERN = "yyyy-MM-dd"

@Throws(ParseException::class)
private fun convertStringToDate(dateString: String): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.parse(dateString)
}

fun dateToString(pattern: String, dateString: String): String? {
    return try {
        val date = convertStringToDate(dateString)
        val dateFormat = DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault()
        )
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        formatter.format(date)
    } catch (e: ParseException) {
        ""
    }
}