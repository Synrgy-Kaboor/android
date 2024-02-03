package com.synrgy.domain.flight.mapper

import com.synrgy.common.model.AirportData
import com.synrgy.data.flight.local.AirportEntity
import com.synrgy.data.flight.model.request.FlightRequest
import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Airport


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun FlightParam.toRequest(): FlightRequest {
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
        id = id,
        code = code,
        name = name,
        timezone = timezone
    )
}

fun Airport.toData(): AirportData {
    return AirportData(
        id = id,
        code = code,
        name = name,
        timezone = timezone
    )
}

fun Airport.toEntity(): AirportEntity {
    return AirportEntity(
        id = id,
        code = code,
        name = name,
        timezone = timezone
    )
}

fun AirportEntity.toDomain(): Airport {
    return Airport(
        id = id,
        code = code,
        name = name,
        timezone = timezone
    )
}

fun AirportResponse.toEntity(): AirportEntity {
    return AirportEntity(
        id = id,
        code = code,
        name = name,
        timezone = timezone
    )
}