package com.synrgy.data.order.model.response

import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.data.flight.model.response.PlaneResponse


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


data class FlightOrderResponse(
    val departureDateTime: String,
    val arrivalDateTime: String,
    val plane: PlaneResponse,
    val originAirport: AirportResponse,
    val destinationAirport: AirportResponse,
)
