package com.synrgy.domain.booking.mapper

import com.synrgy.data.booking.model.response.BookingInfoResponse
import com.synrgy.data.booking.model.response.BookingResponse
import com.synrgy.data.booking.model.response.BookingStatusResponse
import com.synrgy.data.booking.model.response.PassengerResponse
import com.synrgy.data.booking.model.response.PaymentDetailResponse
import com.synrgy.domain.booking.model.response.Booking
import com.synrgy.domain.booking.model.response.BookingInfo
import com.synrgy.domain.booking.model.response.BookingStatus
import com.synrgy.domain.booking.model.response.Passenger
import com.synrgy.domain.booking.model.response.PaymentDetail


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


fun BookingResponse.toDomain(): Booking {
    return Booking(
        bookingId = bookingId
    )
}

fun BookingInfoResponse.toDomain(): BookingInfo {
    return BookingInfo(
        orderer = orderer?.toDomain(),
        passengers = passengers?.map { it.toDomain() },
        totalAdult = totalAdult,
        totalChildren = totalChildren,
        totalBaby = totalBaby,
        addBaggage = addBaggage,
        addTravelInsurance = addTravelInsurance,
        addBaggageInsurance = addBaggageInsurance,
        addDelayProtection = addDelayProtection,
        paymentId = paymentId
    )
}

fun BookingStatusResponse.toDomain(): BookingStatus {
    return BookingStatus(
        methodName = methodName,
        totalPrice = totalPrice,
        expiredTime = expiredTime,
        paymentCompleted = paymentCompleted,
        paymentDateTime = paymentDateTime,
        invoiceNumber = invoiceNumber
    )
}

fun PassengerResponse.toDomain(): Passenger {
    return Passenger(
        fullName = fullName,
        phoneNumber = phoneNumber,
        title = title,
        email = email
    )
}

fun PaymentDetailResponse.toDomain(): PaymentDetail {
    return PaymentDetail(
        methodName = methodName,
        accountNumber = accountNumber,
        totalPrice = totalPrice,
        paymentCompleted = paymentCompleted,
        expiredTime = expiredTime
    )
}