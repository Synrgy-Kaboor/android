package com.synrgy.domain.order.model.response


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


data class Order(
    val id: Int,
    val bookingId: String,
    val flight: FlightOrder,
    val uploadedProofOfPayment: Boolean,
    val paymentCompleted: Boolean,
    val type: String
)
