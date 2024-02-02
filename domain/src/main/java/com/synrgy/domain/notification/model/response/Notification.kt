package com.synrgy.domain.notification.model.response

import androidx.annotation.DrawableRes


/**
 * Created by wahid on 2/2/2024.
 * Github github.com/wahidabd.
 */


data class Notification(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    @DrawableRes val icon: Int
)
