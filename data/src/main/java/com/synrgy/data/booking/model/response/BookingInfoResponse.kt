package com.synrgy.data.booking.model.response


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingInfoResponse(
    val orderer: PassengerResponse? = null,
    val passengers: List<PassengerResponse>? = emptyList(),
    val totalAdult: Int? = 0,
    val totalChildren: Int? = 0,
    val totalBaby: Int? = 0,
    val addBaggage: Boolean? = false,
    val addTravelInsurance: Boolean? = false,
    val addBaggageInsurance: Boolean? = false,
    val addDelayProtection: Boolean? = false,
    val paymentId: Int?
)
