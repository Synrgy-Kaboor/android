package com.synrgy.domain.booking.model.request

import kotlinx.serialization.Serializable


/**
 * Created by wahid on 1/23/2024.
 * Github github.com/wahidabd.
 */

@Serializable
data class PlaneParam(
    val originCity: String,
    val destinationCity: String,
    val departureDate: String,
    val returnDate: String? = null,
    val numOfKids: Int? = 0,
    val numOfBabies: Int? = 0,
    val numOfAdults: Int? = 0,
    val classType: String,
)
