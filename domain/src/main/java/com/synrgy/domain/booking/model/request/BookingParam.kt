package com.synrgy.domain.booking.model.request

import com.synrgy.data.booking.model.request.PassengerRequest


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingParam(
    val outboundFlightId: Int,
    val returnFlightId: Int? = null,
    val classCode: String,
    val totalAdult: Int,
    val totalChild: Int,
    val totalBaby: Int,
    val orderer: PassengerParam,
    val passengers: List<PassengerParam>,
    val addBaggage: Boolean = false,
    val addTravelInsurance: Boolean = false,
    val addDelayProtection: Boolean = false,
    val paymentMethod: String,
    val voucherId: Int? = null
)
