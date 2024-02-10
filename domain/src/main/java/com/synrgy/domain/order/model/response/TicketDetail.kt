package com.synrgy.domain.order.model.response

import com.synrgy.domain.booking.model.response.Passenger


/**
 * Created by wahid on 2/10/2024.
 * Github github.com/wahidabd.
 */


data class TicketDetail(
    val id: String,
    val bookingCode: String,
    val flight: FlightOrder,
    val uploadedProofOfPayment: Boolean,
    val paymentCompleted: Boolean,
    val passengers: List<Passenger>,
    val addBaggage: Boolean,
    val addTravelInsurance: Boolean,
    val addBaggageInsurance: Boolean,
    val addDelayProtection: Boolean,
)
