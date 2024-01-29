package com.synrgy.data.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingRequest(
    val flightId: Int,
    val classCode: String,
    val totalAdult: Int,
    val totalChild: Int,
    val totalBaby: Int,
)
