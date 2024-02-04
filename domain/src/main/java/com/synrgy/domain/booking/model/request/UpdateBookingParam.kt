package com.synrgy.domain.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class UpdateBookingParam(
    val orderer: PassengerParam,
    val passengers: List<PassengerParam>,
    val addBaggage: Boolean,
)
