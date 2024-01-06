package com.synrgy.common.utils.ext

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.visible


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

fun AppCompatActivity.onBackPress() =
    this.onBackPressedDispatcher.onBackPressed()

inline fun Group.onGroupClick(crossinline onClick: () -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).onClick { onClick.invoke() }
    }
}

fun String.lowerContains(char: String): Boolean =
    this.lowercase().contains(char.lowercase())