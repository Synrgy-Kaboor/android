package com.synrgy.domain.flight.model.response

import androidx.annotation.DrawableRes


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class Flight(
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
