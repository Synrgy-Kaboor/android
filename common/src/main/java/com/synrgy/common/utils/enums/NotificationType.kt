package com.synrgy.common.utils.enums

import androidx.annotation.DrawableRes
import com.synrgy.common.R


/**
 * Created by wahid on 2/2/2024.
 * Github github.com/wahidabd.
 */


enum class NotificationType(val label: String) {
    ALL("Semua"),
    PRICE("Notifikasi Harga")
}

enum class NotificationDataType(val label: String, @DrawableRes val icon: Int) {
    PAYMENT("approval", R.drawable.ic_notification_payment),
    DELAY("flight", R.drawable.ic_notification_delay),
    PRICE("price alert", R.drawable.ic_notification_price);

    companion object {
        fun fromLabel(label: String): NotificationDataType {
            return entries.first { it.label == label }
        }
    }
}

enum class PriceAlertType {
    NEW, EDIT
}