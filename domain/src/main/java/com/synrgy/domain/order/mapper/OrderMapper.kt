package com.synrgy.domain.order.mapper

import com.synrgy.data.order.model.response.FlightOrderResponse
import com.synrgy.data.order.model.response.OrderResponse
import com.synrgy.data.order.model.response.TicketDetailResponse
import com.synrgy.domain.booking.mapper.toDomain
import com.synrgy.domain.flight.mapper.toDomain
import com.synrgy.domain.order.model.response.FlightOrder
import com.synrgy.domain.order.model.response.Order
import com.synrgy.domain.order.model.response.TicketDetail


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


fun FlightOrderResponse.toDomain(): FlightOrder {
    return FlightOrder(
        departureDateTime = departureDateTime,
        arrivalDateTime = arrivalDateTime,
        plane = plane.toDomain(),
        originAirport = originAirport.toDomain(),
        destinationAirport = destinationAirport.toDomain()
    )
}

fun OrderResponse.toDomain(): Order {
    return Order(
        id = id,
        bookingId = bookingCode,
        flight = flight.toDomain(),
        uploadedProofOfPayment = uploadedProofOfPayment,
        paymentCompleted = paymentCompleted,
        type = type
    )
}

fun TicketDetailResponse.toDomain(): TicketDetail {
    return TicketDetail(
        id = id,
        bookingCode = bookingCode,
        flight = flight.toDomain(),
        uploadedProofOfPayment = uploadedProofOfPayment,
        paymentCompleted = paymentCompleted,
        passengers = passengers.map { it.toDomain() },
        addBaggage = addBaggage,
        addTravelInsurance = addTravelInsurance,
        addBaggageInsurance = addBaggageInsurance,
        addDelayProtection = addDelayProtection
    )
}