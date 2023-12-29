package com.synrgy.common.utils

import android.view.View
import com.wahidabd.library.utils.exts.gone
import com.wahidabd.library.utils.exts.visible


/**
 * Created by wahid on 12/29/2023.
 * Github github.com/wahidabd.
 */


inline fun View.goneIf(condition: () -> Boolean){
    if (condition.invoke()) gone()
    else visible()
}