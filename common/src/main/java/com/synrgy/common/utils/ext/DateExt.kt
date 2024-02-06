package com.synrgy.common.utils.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


val localeIndonesia = Locale("in", "ID")
val timeNow = System.currentTimeMillis()
val oneWeekMillis = timeNow + 604800000 // timeNow - timeNow % (7 * 24 * 60 * 60 * 1000)
const val oneDayMillis = 24 * 60 * 60 * 1000
val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", localeIndonesia)


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
    val date = dateFormat.parse(this)
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
    val format = dateFormat
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

fun String.toPromoDate(): String {
    val format = dateFormat
    val date = format.parse(this)!!
    val diff = date.time - timeNow
    val days = diff / oneDayMillis
    val hours = (diff % oneDayMillis) / (1000 * 60 * 60)
    val minutes = (diff % (1000 * 60 * 60)) / (1000 * 60)

    return if (days > 0) {
        "Berakhir dalam $days hari"
    } else if (hours > 0) {
        "Berakhir dalam $hours jam"
    } else {
        "Berakhir dalam $minutes menit"
    }
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