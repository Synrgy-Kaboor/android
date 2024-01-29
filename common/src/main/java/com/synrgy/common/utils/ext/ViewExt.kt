package com.synrgy.common.utils.ext

import android.content.ClipData
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.kennyc.view.MultiStateView
import com.synrgy.common.R
import com.synrgy.common.utils.enums.ClipboardType
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.exts.clipboardManager
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.invisible
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.onTextChange
import com.wahidabd.library.utils.exts.visible
import kotlinx.coroutines.flow.callbackFlow


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

fun TextInputEditText.textTrim() = this.text.toString().trim()

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

fun MaterialTextView.toStringTrim() = this.text.toString().trim()

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


fun AppCompatActivity.copyTextToClipboard(text: String, type: ClipboardType) {
    val clipData = ClipData.newPlainText("text", text)
    this.clipboardManager.setPrimaryClip(clipData)
    snackbarSuccess(getString(type.message))
}

fun AppCompatActivity.snackbarSuccess(message: String) {
    Snackbar.make(this.window.decorView.rootView, message, Snackbar.LENGTH_LONG).apply {
        setBackgroundTint(ContextCompat.getColor(this@snackbarSuccess, R.color.secondarySuccess))
        setTextColor(ContextCompat.getColor(this@snackbarSuccess, R.color.neutral1))
    }.show()
}

fun AppCompatActivity.snackbarDanger(message: String) {
    Snackbar.make(this.window.decorView.rootView, message, Snackbar.LENGTH_LONG).apply {
        setBackgroundTint(ContextCompat.getColor(this@snackbarDanger, R.color.secondaryDanger))
        setTextColor(ContextCompat.getColor(this@snackbarDanger, R.color.neutral1))
    }.show()
}

fun String.chuckedString(): String = this.chunked(4).joinToString(" ")

fun View.showHideToggle() {
    if (this.visibility == View.VISIBLE) this.gone()
    else this.visible()
}

inline fun MultiStateView.showLoginState(crossinline action: () -> Unit){
    this.viewState = MultiStateView.ViewState.ERROR
    this.getView(MultiStateView.ViewState.ERROR)?.findViewById<View>(R.id.btn_msv_login)?.onClick {
        action.invoke()
    }
}