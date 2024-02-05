package com.synrgy.data.flight.model.response


/**
 * Created by wahid on 2/4/2024.
 * Github github.com/wahidabd.
 */


data class FlightResponse(
    val id: Int,
    val departureDatetime: String,
    val arrivalDatetime: String,
    val plane: PlaneResponse,
    val originAirport: AirportResponse,
    val destinationAirport: AirportResponse,
    val adultPrice: Long,
    val childPrice: Long,
    val babyPrice: Long
)
