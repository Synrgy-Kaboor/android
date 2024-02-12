package com.synrgy.domain.notification.model.response

import androidx.annotation.DrawableRes
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 2/2/2024.
 * Github github.com/wahidabd.
 */


data class Notification(
    val created_at: String,
    val deleted_at: String? = emptyString(),
    val detail: String,
    val flag: Boolean,
    val id: String,
    val price_notification_id: Int? = 0,
    val title: String,
    val type: String,
    val updated_at: String,
    val user_id: String
)
