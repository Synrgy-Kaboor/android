package com.synrgy.domain.booking.mapper

import com.synrgy.data.booking.model.request.BookingRequest
import com.synrgy.data.booking.model.request.UpdateAdditionalBookingRequest
import com.synrgy.data.booking.model.request.UpdateBookingRequest
import com.synrgy.data.booking.model.request.PassengerRequest
import com.synrgy.data.booking.model.request.UpdatePaymentMethodRequest
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.booking.model.request.UpdateAdditionalBookingParam
import com.synrgy.domain.booking.model.request.UpdateBookingParam
import com.synrgy.domain.booking.model.request.PassengerParam
import com.synrgy.domain.booking.model.request.UpdatePaymentMethodParam
import com.synrgy.domain.booking.model.response.Passenger


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


fun BookingParam.toRequest(): BookingRequest {
    return BookingRequest(
        outboundFlightId = outboundFlightId,
        returnFlightId = returnFlightId,
        classCode = classCode,
        totalAdult = totalAdult,
        totalChild = totalChild,
        totalBaby = totalBaby,
        orderer = orderer.toRequest(),
        passengers = passengers.map { it.toRequest() },
        addBaggage = addBaggage,
        addTravelInsurance = addTravelInsurance,
        addDelayProtection = addDelayProtection,
        addBaggageInsurance = addBaggageInsurance,
        paymentMethod = paymentMethod,
        voucherId = voucherId
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

fun PassengerParam.toRequest(): PassengerRequest {
    return PassengerRequest(
        fullName = fullName,
        title = title,
        phoneNumber = phoneNumber,
        email = email
    )
}

fun UpdatePaymentMethodParam.toRequest(): UpdatePaymentMethodRequest {
    return UpdatePaymentMethodRequest(
        paymentMethod = paymentMethod
    )
}

fun Passenger.toParam(): PassengerParam {
    return PassengerParam(
        fullName = fullName,
        title = title,
        phoneNumber = phoneNumber,
        email = email
    )
}