package com.synrgy.common.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.synrgy.common.R


/**
 * Created by wahid on 12/30/2023.
 * Github github.com/wahidabd.
 */


data class MenuHome(
    val id: Int,
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
)
