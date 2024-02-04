package com.synrgy.data.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class UpdateBookingRequest(
    val orderer: PassengerRequest,
    val passengers: List<PassengerRequest>,
    val addBaggage: Boolean,
)
