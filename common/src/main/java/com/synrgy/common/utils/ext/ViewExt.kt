package com.synrgy.common.utils.ext

import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.invisible
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible
import kotlin.time.Duration.Companion.milliseconds


/**
 * Created by wahid on 12/29/2023.
 * Github github.com/wahidabd.
 */


inline fun View.goneIf(condition: () -> Boolean){
    if (condition.invoke()) gone()
    else visible()
}

inline fun View.visibleIf(condition: () -> Boolean){
    if (condition.invoke()) visible()
    else gone()
}

inline fun View.invisibleIf(condition: () -> Boolean){
    if (condition.invoke()) invisible()
    else visible()
}

fun AppCompatActivity.onBackPress() =
    this.onBackPressedDispatcher.onBackPressed()

inline fun Group.onGroupClick(crossinline onClick: () -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).onClick { onClick.invoke() }
    }
}

fun String.lowerContains(char: String): Boolean =
    this.lowercase().contains(char.lowercase())

fun AppCompatActivity.showDatePicker(
    onClick: (Long) -> Unit
){
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

    with(datePicker){
        show(supportFragmentManager, datePicker.toString())
        addOnPositiveButtonClickListener { onClick(it) }
    }
}

fun TextInputEditText.textTrim() = this.text.toString().trim()

fun setTimer(
    millisTimer: Long,
    interval: Long,
    onTick: ((Long) -> Unit) = {},
    onFinish: (() -> Unit) = {}
): CountDownTimer{
    return object : CountDownTimer(millisTimer, interval){
        override fun onTick(millisUntilFinished: Long) = onTick.invoke(millisUntilFinished)
        override fun onFinish() = onFinish.invoke()
    }
}

fun Long.toSeconds(): String {
    return this.milliseconds.toComponents { _, minutes, seconds, _ ->
        "%02d:%02d".format(minutes, seconds)
    }
}
