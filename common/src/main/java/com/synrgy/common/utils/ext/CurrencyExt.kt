package com.synrgy.common.utils.ext

import java.text.DecimalFormat
import java.text.NumberFormat


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun Float.toCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance(localeIndonesia)
    formatter.minimumFractionDigits = 0
    return formatter.format(this)
}