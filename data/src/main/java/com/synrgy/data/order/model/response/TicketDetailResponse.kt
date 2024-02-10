package com.synrgy.data.order.model.response

import com.synrgy.data.booking.model.response.PassengerResponse


/**
 * Created by wahid on 2/10/2024.
 * Github github.com/wahidabd.
 */


data class TicketDetailResponse(
    val id: String,
    val bookingCode: String,
    val flight: FlightOrderResponse,
    val uploadedProofOfPayment: Boolean,
    val paymentCompleted: Boolean,
    val passengers: List<PassengerResponse>,
    val addBaggage: Boolean,
    val addTravelInsurance: Boolean,
    val addBaggageInsurance: Boolean,
    val addDelayProtection: Boolean,
)
