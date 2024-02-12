package com.synrgy.domain.order.model.response

import com.synrgy.domain.flight.model.response.Airport
import com.synrgy.domain.flight.model.response.Plane


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


data class FlightOrder(
    val departureDateTime: String,
    val arrivalDateTime: String,
    val plane: Plane,
    val originAirport: Airport,
    val destinationAirport: Airport,
)
