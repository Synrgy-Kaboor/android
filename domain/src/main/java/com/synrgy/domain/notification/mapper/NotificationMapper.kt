package com.synrgy.domain.notification.mapper

import com.synrgy.data.notification.model.request.AirportCityRequest
import com.synrgy.data.notification.model.request.PriceNotificationRequest
import com.synrgy.data.notification.model.response.AirportCityResponse
import com.synrgy.data.notification.model.response.NotificationDataResponse
import com.synrgy.data.notification.model.response.PriceNotificationResponse
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.notification.model.request.AirportCityParam
import com.synrgy.domain.notification.model.request.PriceNotificationParam
import com.synrgy.domain.notification.model.response.AirportCity
import com.synrgy.domain.notification.model.response.Notification
import com.synrgy.domain.notification.model.response.PriceNotification


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


fun AirportCityResponse.toDomain(): AirportCity {
    return AirportCity(
        name = name,
        code = code
    )
}

fun PriceNotificationResponse.toDomain(): PriceNotification {
    return PriceNotification(
        originCity = originCity.toDomain(),
        destinationCity = destinationCity.toDomain(),
        departureDate = departureDate,
        returnDate = returnDate,
        numOfAdults = numOfAdults,
        numOfKids = numOfKids,
        numOfBabies = numOfBabies,
        clazz = clazz,
        lowerPriceLimit = lowerPriceLimit,
        upperPriceLimit = upperPriceLimit
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
        originCity = originCity.toRequest(),
        destinationCity = destinationCity.toRequest(),
        departureDate = departureDate,
        returnDate = returnDate,
        numOfAdults = numOfAdults,
        numOfKids = numOfKids,
        numOfBabies = numOfBabies,
        clazz = clazz,
        lowerPriceLimit = lowerPriceLimit,
        upperPriceLimit = upperPriceLimit
    )
}

fun PriceNotification.toFlightParam(): FlightParam {
    return FlightParam(
        originCity = originCity.name,
        destinationCity = destinationCity.name,
        departureDate = departureDate,
        returnDate = returnDate,
        numOfAdults = numOfAdults,
        numOfKids = numOfKids,
        numOfBabies = numOfBabies,
        classCode = clazz
    )
}

fun NotificationDataResponse.toDomain(): Notification {
    return Notification(
        created_at = created_at,
        deleted_at = deleted_at,
        detail = detail,
        flag = flag,
        id = id,
        price_notification_id = price_notification_id,
        title = title,
        type = type,
        updated_at = updated_at,
        user_id = user_id
    )
}