package com.synrgy.domain.booking

import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.booking.model.request.UpdateAdditionalBookingParam
import com.synrgy.domain.booking.model.request.UpdateBookingParam
import com.synrgy.domain.booking.model.request.UpdatePaymentMethodParam
import com.synrgy.domain.booking.model.response.Booking
import com.synrgy.domain.booking.model.response.BookingInfo
import com.synrgy.domain.booking.model.response.BookingStatus
import com.synrgy.domain.booking.model.response.PaymentDetail
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


interface BookingUseCase {

    suspend fun createBooking(
        body: BookingParam
    ): Flow<Resource<Booking>>

    suspend fun getBookingInfo(
        id: Int
    ): Flow<Resource<BookingInfo>>

    suspend fun updateBooking(
        id: Int,
        body: UpdateBookingParam
    ): Flow<Resource<KaboorGenericResponse>>

    suspend fun updateAdditionalService(
        id: Int,
        body: UpdateAdditionalBookingParam
    ): Flow<Resource<KaboorGenericResponse>>

    suspend fun updatePayment(
        id: Int,
        body: UpdatePaymentMethodParam
    ): Flow<Resource<KaboorGenericResponse>>

    suspend fun getPaymentDetail(
        id: Int
    ): Flow<Resource<PaymentDetail>>

    suspend fun getBookingStatus(
        id: Int
    ): Flow<Resource<BookingStatus>>
}