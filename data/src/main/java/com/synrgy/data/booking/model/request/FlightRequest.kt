package com.synrgy.data.booking.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


data class FlightRequest(
    val originCity: String,
    val destinationCity: String,
    val departureDate: String,
    val returnDate: String? = null,
    val numOfKids: Int? = 0,
    val numOfBabies: Int? = 0,
    val numOfAdults: Int? = 0,
    val classCode: String,
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "originCity" to originCity,
            "destinationCity" to destinationCity,
            "departureDate" to departureDate,
            "returnDate" to returnDate,
            "numOfKids" to numOfKids,
            "numOfBabies" to numOfBabies,
            "numOfAdults" to numOfAdults,
            "classCode" to classCode
        )
    }
}
