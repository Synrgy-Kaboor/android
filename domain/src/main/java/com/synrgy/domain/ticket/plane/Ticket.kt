package com.synrgy.domain.ticket.plane

import androidx.annotation.DrawableRes

data class Ticket(
    val id: Int,
    val plane: String,
    val typeClass: String,
    val departure: String,
    val departureTime: String,
    val destination: String,
    val destinationTime: String,
    val boardingTime: String,
    val date: String? = null,
    val price: Long,
    @DrawableRes val image: Int? = null
)