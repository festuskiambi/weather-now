package com.example.weathernow.util

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

val GENERAL_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
const val DATE_24_PATTERN = "MMMM dd, HH:mm"
const val DATE_AM_PM_PATTERN = "MMMM dd, h:mm a"
const val HOUR_PATTERN = "EEE HH:mm"
const val DAY_NAME_IN_WEEK_PATTERN = "EEE"
const val DAY_DATE_PATTERN = "yyyy-MM-dd"


//fun String.toDate(pattern: String, date: String): String {
//    val formatter = DateTimeFormatter.ofPattern(pattern)
//    return formatter.format(date)
//}

@Throws(ParseException::class)
private fun convertStringToDate(dateString: String): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.parse(dateString)
}

fun dateToString(pattern: String,dateString: String): String? {
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