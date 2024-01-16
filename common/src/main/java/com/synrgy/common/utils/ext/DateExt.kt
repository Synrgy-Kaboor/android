package com.synrgy.common.utils.ext

import java.util.Locale


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */


val localeIndonesia = Locale("in", "ID")
val timeNow = System.currentTimeMillis()
val tomorrowMillis = timeNow + 86400000 // timeNow - timeNow % (24 * 60 * 60 * 1000)
val oneWeekMillis = timeNow + 604800000 // timeNow - timeNow % (7 * 24 * 60 * 60 * 1000)


fun Long.toFullDateFormat(): String {
    return java.text.SimpleDateFormat("EEEE, dd MMM yyyy", localeIndonesia).format(this)
}