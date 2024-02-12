package com.synrgy.domain.notification.mapper

import com.synrgy.data.notification.model.request.AirportCityRequest
import com.synrgy.data.notification.model.request.PriceNotificationRequest
import com.synrgy.data.notification.model.response.AirportCityResponse
import com.synrgy.data.notification.model.response.PriceNotificationResponse
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.notification.model.request.AirportCityParam
import com.synrgy.domain.notification.model.request.PriceNotificationParam
import com.synrgy.domain.notification.model.response.AirportCity
import com.synrgy.domain.notification.model.response.PriceNotification


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


fun AirportCityResponse.toDomain(): AirportCity {
    return AirportCity(
        id = id,
        code = code,
        name = name,
        timezone = timezone,
    )
}

fun PriceNotificationResponse.toDomain(): PriceNotification {
    return PriceNotification(
        id = id,
        totalAdults = totalAdults,
        totalChildren = totalChildren,
        totalBabies = totalBabies,
        classCode = classCode,
        minimumPrice = minimumPrice,
        maximumPrice = maximumPrice,
        date = date,
        originAirport = originAirport.toDomain(),
        destinationAirport = destinationAirport.toDomain()
    )
}

fun AirportCityParam.toRequest(): AirportCityRequest {
    return AirportCityRequest(
        name = name,
        code = code
    )
}

fun PriceNotificationParam.toRequest(): PriceNotificationRequest {
    return PriceNotificationRequest(
        totalAdults = totalAdults,
        totalChildren = totalChildren,
        totalBabies = totalBabies,
        classCode = classCode,
        minimumPrice = minimumPrice,
        maximumPrice = maximumPrice,
        date = date,
        originAirportId = originAirportId,
        destinationAirportId = destinationAirportId
    )
}

fun PriceNotification.toFlightParam(): FlightParam {
    return FlightParam(
        originCity = originAirport.name,
        destinationCity = destinationAirport.name,
        departureDate = date,
        returnDate = date,
        numOfAdults = totalAdults,
        numOfKids = totalBabies,
        numOfBabies = totalBabies,
        classCode = classCode
    )
}