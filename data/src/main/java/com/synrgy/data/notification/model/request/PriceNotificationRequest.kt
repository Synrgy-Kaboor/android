package com.synrgy.data.notification.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class PriceNotificationRequest(
    val totalAdults: Int? = 0,
    val totalChildren: Int? = 0,
    val totalBabies: Int? = 0,
    @SerializedName("class")
    val classCode: String? = null,
    val minimumPrice: Long? = 0L,
    val maximumPrice: Long? = 0L,
    val date: String? = null,
    val originAirportId: Int? = 0,
    val destinationAirportId: Int? = 0,
)
