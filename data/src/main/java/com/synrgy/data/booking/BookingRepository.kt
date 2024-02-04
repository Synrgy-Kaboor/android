package com.synrgy.data.booking

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.booking.model.request.BookingRequest
import com.synrgy.data.booking.model.request.UpdateAdditionalBookingRequest
import com.synrgy.data.booking.model.request.UpdateBookingRequest
import com.synrgy.data.booking.model.request.UpdatePaymentMethodRequest
import com.synrgy.data.booking.model.response.BookingInfoResponse
import com.synrgy.data.booking.model.response.BookingResponse
import com.synrgy.data.booking.model.response.BookingStatusResponse
import com.synrgy.data.booking.model.response.PaymentDetailResponse
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


interface BookingRepository {
    suspend fun createBooking(
        body: BookingRequest
    ): Flow<Resource<ResponseWrapper<BookingResponse>>>

    suspend fun getBookingInfo(
        id: Int
    ): Flow<Resource<ResponseWrapper<BookingInfoResponse>>>

    suspend fun updateBooking(
        id: Int,
        body: UpdateBookingRequest
    ): Flow<Resource<KaboorGenericResponse>>

    suspend fun updateAdditionalService(
        id: Int,
        body: UpdateAdditionalBookingRequest
    ): Flow<Resource<KaboorGenericResponse>>

    suspend fun updatePayment(
        id: Int,
        body: UpdatePaymentMethodRequest
    ): Flow<Resource<KaboorGenericResponse>>

    suspend fun getPaymentDetail(
        id: Int
    ): Flow<Resource<ResponseWrapper<PaymentDetailResponse>>>

    suspend fun getBookingStatus(
        id: Int
    ): Flow<Resource<ResponseWrapper<BookingStatusResponse>>>
}