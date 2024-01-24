package com.synrgy.common.utils.ext

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import kotlin.time.Duration.Companion.milliseconds


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun AppCompatActivity.showDatePicker(
    onClick: (Long) -> Unit
) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

    with(datePicker) {
        show(supportFragmentManager, datePicker.toString())
        addOnPositiveButtonClickListener { onClick(it) }
    }
}

fun Long.toSeconds(): String {
    return this.milliseconds.toComponents { _, minutes, seconds, _ ->
        "%02d:%02d".format(minutes, seconds)
    }
}

fun Long.toMinutes(): String {
    return this.milliseconds.toComponents{ hours, minutes, seconds, _ ->
        "%02d:%02d:%02d".format(hours, minutes, seconds)
    }
}