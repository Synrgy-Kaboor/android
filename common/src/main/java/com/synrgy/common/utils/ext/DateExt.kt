package com.synrgy.common.utils.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


val localeIndonesia = Locale("in", "ID")
val timeNow = System.currentTimeMillis()
const val oneDayMillis = 24 * 60 * 60 * 1000
val oneWeekMillis = timeNow + 604800000 // timeNow - timeNow % (7 * 24 * 60 * 60 * 1000)


fun Long.toFullDateFormat(): String {
    return SimpleDateFormat("EEEE, dd MMM yyyy", localeIndonesia).format(this)
}

fun Long.toDateFormat(): String {
    return SimpleDateFormat("dd/MM/yyyy", localeIndonesia).format(this)
}

fun Long.toDateFormatMonth(): String {
    return SimpleDateFormat("yyyy-MM-dd", localeIndonesia).format(this)
}

fun String.toHeaderBookingDate(): String {
    return SimpleDateFormat("EE, dd MMM", localeIndonesia).format(
        SimpleDateFormat("dd-mm-yyyy", localeIndonesia).parse(this)!!
    )
}

fun String.toEpochMillis(): Long {
    return SimpleDateFormat("dd-mm-yyyy", localeIndonesia).parse(this)?.time!!
}

fun String.toHourMinuteFormat(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", localeIndonesia)
    val date = formatter.parse(this)
    return SimpleDateFormat("HH:mm", localeIndonesia).format(date as Date)
}

fun Int.toGmtFormat(date: String): String {
    return if (this < 0) {
        "${date.toHourMinuteFormat()} GMT-${this * -1}"
    } else {
        "${date.toHourMinuteFormat()} GMT+${this}"
    }
}

fun convertToDuration(start: String, end: String): String{
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", localeIndonesia)
    val startTime = format.parse(start)!!
    val endTime = format.parse(end)!!
    val duration = endTime.time - startTime.time

    val hours = duration / (1000 * 3600)
    val minutes = (duration % (1000 * 3600)) / (1000 * 60)

    val hourString = if (hours > 1) "$hours Jam" else ""
    val minuteString = if (minutes > 1) "$minutes Menit" else ""

    return "Durasi $hourString $minuteString"
//    String.format("%d Jam %d Menit", hours, minutes)
}

fun String.toGreeting(): String {
    val hourOfDay = Calendar.getInstance().apply {
        timeInMillis = timeNow
    }.get(Calendar.HOUR_OF_DAY)

    return when (hourOfDay) {
        in 0..5 -> "Selamat Malam, $this"
        in 6..9 -> "Selamat Pagi, $this"
        in 10..14 -> "Selamat Siang, $this"
        in 15..18 -> "Selamat Sore, $this"
        else -> "Selamat Malam, $this"
    }
}