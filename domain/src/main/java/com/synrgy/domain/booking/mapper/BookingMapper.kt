package com.synrgy.domain.booking.mapper

import com.synrgy.common.model.AirportData
import com.synrgy.data.booking.model.request.FlightRequest
import com.synrgy.data.booking.model.response.AirportResponse
import com.synrgy.domain.booking.model.request.FlightParam
import com.synrgy.domain.booking.model.response.Airport


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun FlightParam.toRequest(): FlightRequest{
    return FlightRequest(
        originCity = this.originCity,
        destinationCity = this.destinationCity,
        departureDate = this.departureDate,
        returnDate = this.returnDate,
        numOfKids = this.numOfKids,
        numOfBabies = this.numOfBabies,
        numOfAdults = this.numOfAdults,
        classCode = this.classCode
    )
}

fun AirportResponse.toDomain(): Airport {
    return Airport(
        city = this.city,
        airport = this.airport
    )
}

fun Airport.toData(): AirportData {
    return AirportData(
        city = this.city,
        airport = this.airport
    )
}