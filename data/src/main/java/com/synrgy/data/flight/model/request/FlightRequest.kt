package com.synrgy.data.flight.model.request


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


data class FlightRequest(
    val originCity: String,
    val destinationCity: String,
    val departureDate: String,
    val returnDate: String,
    val numOfKids: Int? = 0,
    val numOfBabies: Int? = 0,
    val numOfAdults: Int? = 0,
    val classCode: String,
    val isReturn: Boolean = false
){
    fun toMap(): Map<String, String> {
        return mapOf(
            "originAirportCode" to if (!isReturn) originCity else destinationCity,
            "destinationAirportCode" to if (!isReturn) destinationCity else originCity,
            "numOfAdults" to numOfAdults.toString(),
            "numOfChildren" to numOfKids.toString(),
            "numOfBabies" to numOfBabies.toString(),
            "classCode" to classCode,
            "date" to if (!isReturn) departureDate else returnDate
        )
    }
}
