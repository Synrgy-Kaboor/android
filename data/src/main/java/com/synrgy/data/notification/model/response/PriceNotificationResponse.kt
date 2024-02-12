package com.synrgy.data.notification.model.response


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class PriceNotificationResponse(
    val id: Int? = 0,
    val totalAdults: Int? = 0,
    val totalChildren: Int? = 0,
    val totalBabies: Int? = 0,
    val classCode: String,
    val minimumPrice: Long? = 0L,
    val maximumPrice: Long? = 0L,
    val date: String,
    val originAirport: AirportCityResponse,
    val destinationAirport: AirportCityResponse,
)
