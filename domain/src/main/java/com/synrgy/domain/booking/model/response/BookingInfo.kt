package com.synrgy.domain.booking.model.response


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingInfo(
    val orderer: Passenger? = null,
    val passengers: List<Passenger>? = emptyList(),
    val totalAdult: Int? = 0,
    val totalChildren: Int? = 0,
    val totalBaby: Int? = 0,
    val addBaggage: Boolean? = false,
    val addTravelInsurance: Boolean? = false,
    val addBaggageInsurance: Boolean? = false,
    val addDelayProtection: Boolean? = false,
    val paymentId: Int? = null
)
