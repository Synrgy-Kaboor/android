package com.synrgy.common.utils.ext

import java.text.NumberFormat


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun Float.toCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance(localeIndonesia)
    formatter.minimumFractionDigits = 0
    val formattedString = formatter.format(this)
    return formattedString.replace("Rp", "Rp ")
}

fun Long.toCurrency(isFormat: Boolean = true): String {
    val formatter = NumberFormat.getCurrencyInstance(localeIndonesia)
    formatter.minimumFractionDigits = 0
    val formattedString = formatter.format(this)

    return if (isFormat) formattedString.replace("Rp", "Rp ")
    else formattedString.replace("Rp", "")
}

fun calculatePlanePrice(vararg data: Pair<Long, Int>): Long {
    var result = 0L
    for (i in data) {
        val times = i.first * i.second
        result += times
    }

    return result
}