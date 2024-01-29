package com.synrgy.common.utils.ext

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


val localeIndonesia = Locale("in", "ID")
val timeNow = System.currentTimeMillis()
val oneDay = timeNow % (24 * 60 * 60 * 1000)
val tomorrowMillis = timeNow + oneDay // timeNow - timeNow % (24 * 60 * 60 * 1000)
val oneWeekMillis = timeNow + 604800000 // timeNow - timeNow % (7 * 24 * 60 * 60 * 1000)


fun Long.toFullDateFormat(): String {
    return SimpleDateFormat("EEEE, dd MMM yyyy", localeIndonesia).format(this)
}

fun Long.toDateFormat(): String {
    return SimpleDateFormat("dd/MM/yyyy", localeIndonesia).format(this)
}

fun Long.toDateFormatMonth(): String {
    return SimpleDateFormat("dd-mm-yyyy", localeIndonesia).format(this)
}

fun String.toHeaderBookingDate(): String {
    return SimpleDateFormat("EE, dd MMM", localeIndonesia).format(
        SimpleDateFormat("dd-mm-yyyy", localeIndonesia).parse(this)!!
    )
}

fun String.toEpochMillis(): Long {
    return SimpleDateFormat("dd-mm-yyyy", localeIndonesia).parse(this)?.time!!
}