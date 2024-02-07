package com.synrgy.data.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingRequest(
    val outboundFlightId: Int,
    val returnFlightId: Int? = null,
    val classCode: String,
    val totalAdult: Int,
    val totalChild: Int,
    val totalBaby: Int,
    val orderer: PassengerRequest,
    val passengers: List<PassengerRequest>,
    val addBaggage: Boolean = false,
    val addTravelInsurance: Boolean = false,
    val addDelayProtection: Boolean = false,
    val addBaggageInsurance: Boolean = false,
    val paymentMethod: String? = null,
    val voucherId: Int? = null
)
