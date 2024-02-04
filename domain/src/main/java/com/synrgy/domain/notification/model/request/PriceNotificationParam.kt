package com.synrgy.domain.notification.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class PriceNotificationParam(
    val originCity: AirportCityParam,
    val destinationCity: AirportCityParam,
    val departureDate: String,
    val returnDate: String? = null,
    val numOfAdults: Int? = 0,
    val numOfKids: Int? = 0,
    val numOfBabies: Int? = 0,
    @SerializedName("class")
    val clazz: String,
    val lowerPriceLimit: Long? = 0L,
    val upperPriceLimit: Long? = 0L,
)
