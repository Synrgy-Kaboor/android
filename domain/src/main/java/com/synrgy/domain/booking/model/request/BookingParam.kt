package com.synrgy.domain.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingParam(
    val flightId: Int,
    val classCode: String,
    val totalAdult: Int,
    val totalChild: Int,
    val totalBaby: Int,
)
