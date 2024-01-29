package com.synrgy.domain.booking.mapper

import com.synrgy.data.booking.model.request.BookingRequest
import com.synrgy.data.booking.model.request.UpdateAdditionalBookingRequest
import com.synrgy.data.booking.model.request.UpdateBookingRequest
import com.synrgy.data.booking.model.request.UpdatePassengerRequest
import com.synrgy.data.booking.model.request.UpdatePaymentMethodRequest
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.booking.model.request.UpdateAdditionalBookingParam
import com.synrgy.domain.booking.model.request.UpdateBookingParam
import com.synrgy.domain.booking.model.request.UpdatePassengerParam
import com.synrgy.domain.booking.model.request.UpdatePaymentMethodParam


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun BookingParam.toRequest(): BookingRequest {
    return BookingRequest(
        flightId = flightId,
        classCode = classCode,
        totalAdult = totalAdult,
        totalChild = totalChild,
        totalBaby = totalBaby
    )
}

fun UpdateAdditionalBookingParam.toRequest(): UpdateAdditionalBookingRequest {
    return UpdateAdditionalBookingRequest(
        addTravelInsurance = addTravelInsurance,
        addBaggageInsurance = addBaggageInsurance,
        addDelayProtection = addDelayProtection
    )
}

fun UpdateBookingParam.toRequest(): UpdateBookingRequest {
    return UpdateBookingRequest(
        orderer = orderer.toRequest(),
        passengers = passengers.map { it.toRequest() },
        addBaggage = addBaggage
    )
}

fun UpdatePassengerParam.toRequest(): UpdatePassengerRequest {
    return UpdatePassengerRequest(
        fullName = fullName,
        title = title,
        phone_number = phone_number,
        email = email
    )
}

fun UpdatePaymentMethodParam.toRequest(): UpdatePaymentMethodRequest {
    return UpdatePaymentMethodRequest(
        paymentMethod = paymentMethod
    )
}