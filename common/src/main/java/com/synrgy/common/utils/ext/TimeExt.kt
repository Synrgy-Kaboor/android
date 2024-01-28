package com.synrgy.common.utils.ext

import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.synrgy.common.R
import kotlin.time.Duration.Companion.milliseconds


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun AppCompatActivity.showDatePicker(
    startDate: Long? = null,
    onClick: (Long) -> Unit
) {

    val constraint = CalendarConstraints.Builder()
        .setValidator(DateValidatorPointForward.from(startDate ?: 0L))
        .build()

    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .setCalendarConstraints(constraint)
        .setTheme(R.style.KaboorCalendar)
        .build()

    with(datePicker) {
        show(supportFragmentManager, datePicker.toString())
        addOnPositiveButtonClickListener { onClick(it) }
    }
}

fun setTimer(
    millisTimer: Long,
    interval: Long,
    onTick: ((Long) -> Unit) = {},
    onFinish: (() -> Unit) = {}
): CountDownTimer {
    return object : CountDownTimer(millisTimer, interval) {
        override fun onTick(millisUntilFinished: Long) = onTick.invoke(millisUntilFinished)
        override fun onFinish() = onFinish.invoke()
    }
}

fun Long.toSeconds(): String {
    return this.milliseconds.toComponents { _, minutes, seconds, _ ->
        "%02d:%02d".format(minutes, seconds)
    }
}

fun Long.toMinutes(): String {
    return this.milliseconds.toComponents { hours, minutes, seconds, _ ->
        "%02d:%02d:%02d".format(hours, minutes, seconds)
    }
}