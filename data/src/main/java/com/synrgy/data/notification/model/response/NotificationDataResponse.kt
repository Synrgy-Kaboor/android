package com.synrgy.data.notification.model.response

data class NotificationDataResponse(
    val created_at: String,
    val deleted_at: String? = null,
    val detail: String,
    val flag: Boolean,
    val id: String,
    val price_notification_id: Int? = null,
    val title: String,
    val type: String,
    val updated_at: String,
    val user_id: String
)