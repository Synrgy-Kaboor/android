package com.synrgy.data.order.model.response


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


data class OrderResponse(
    val id: Int,
    val bookingCode: String,
    val flight: FlightOrderResponse,
    val uploadedProofOfPayment: Boolean,
    val paymentCompleted: Boolean,
    val type: String
)
