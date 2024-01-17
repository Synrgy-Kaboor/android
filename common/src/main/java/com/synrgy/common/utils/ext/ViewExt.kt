package com.synrgy.common.utils.ext

import android.os.CountDownTimer
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.invisible
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.onTextChange
import com.wahidabd.library.utils.exts.setMargins
import com.wahidabd.library.utils.exts.visible
import kotlin.time.Duration.Companion.milliseconds


/**
 * Created by wahid on 12/29/2023.
 * Github github.com/wahidabd.
 */


inline fun View.goneIf(condition: () -> Boolean) {
    if (condition.invoke()) gone()
    else visible()
}

inline fun View.visibleIf(condition: () -> Boolean) {
    if (condition.invoke()) visible()
    else gone()
}

inline fun View.invisibleIf(condition: () -> Boolean) {
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
) {
    val datePicker = MaterialDatePicker.Builder.datePicker()
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

    with(datePicker) {
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

fun TextInputLayout.setEndIcon(@DrawableRes icon: Int) {
    this.endIconDrawable = ContextCompat.getDrawable(context, icon)
}

fun TextInputLayout.endIconClick(onClick: () -> Unit) {
    this.setEndIconOnClickListener { onClick.invoke() }
}

fun setClearPaddingTextInput(list: List<TextInputLayout>) {
    list.forEach { til ->
        til.onTextChange {
            til.clearErrorPadding()
        }
    }
}

fun TextInputLayout.clearErrorPadding() {
    isErrorEnabled = !(this.error == null || this.error == emptyString())
}

fun removeErrorTextPadding(listOfTextInput: List<TextInputLayout>) {
    listOfTextInput.forEach { til ->
        til.apply {
            viewTreeObserver.addOnGlobalLayoutListener {
                if (childCount > 1) {
                    getChildAt(1)?.let {
                        setPadding(0, 4, 0, 0)
                    }
                }
            }
        }
    }
}
