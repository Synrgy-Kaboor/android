package com.synrgy.domain.flight.mapper

import com.synrgy.common.model.AirportData
import com.synrgy.data.flight.local.AirportEntity
import com.synrgy.data.flight.model.response.AirlineResponse
import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.data.flight.model.response.FlightResponse
import com.synrgy.data.flight.model.response.PlaneResponse
import com.synrgy.domain.flight.model.response.Airline
import com.synrgy.domain.flight.model.response.Airport
import com.synrgy.domain.flight.model.response.Flight
import com.synrgy.domain.flight.model.response.Plane


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


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

fun FlightResponse.toDomain(): Flight {
    return Flight(
        id = id,
        plane = plane.toDomain(),
        originAirport = originAirport.toDomain(),
        destinationAirport = destinationAirport.toDomain(),
        departureDatetime = departureDatetime,
        arrivalDatetime = arrivalDatetime,
        adultPrice = adultPrice,
        childPrice = childPrice,
        babyPrice = babyPrice
    )
}

fun PlaneResponse.toDomain(): Plane {
    return Plane(
        id = id,
        name = name,
        code = code,
        airline = airline?.toDomain()
    )
}

fun AirlineResponse.toDomain(): Airline {
    return Airline(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}