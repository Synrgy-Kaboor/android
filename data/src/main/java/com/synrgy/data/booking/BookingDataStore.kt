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
import com.synrgy.data.booking.remote.BookingService
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


class BookingDataStore(
    private val api: BookingService,
    private val error: ErrorParser
) : BookingRepository {

    override suspend fun createBooking(
        body: BookingRequest
    ): Flow<Resource<ResponseWrapper<BookingResponse>>> = flow {
        enqueue(
            body,
            error::convertGenericError,
            api::createBooking,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun getBookingInfo(
        id: Int
    ): Flow<Resource<ResponseWrapper<BookingInfoResponse>>> = flow {
        enqueue(
            id,
            error::convertGenericError,
            api::getBookingInfo,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun updateBooking(
        id: Int,
        body: UpdateBookingRequest
    ): Flow<Resource<KaboorGenericResponse>> = flow {
        enqueue(
            id,
            body,
            error::convertGenericError,
            api::updateBooking,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun updateAdditionalService(
        id: Int,
        body: UpdateAdditionalBookingRequest
    ): Flow<Resource<KaboorGenericResponse>> = flow {
        enqueue(
            id,
            body,
            error::convertGenericError,
            api::updateAdditionalService,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun updatePayment(
        id: Int,
        body: UpdatePaymentMethodRequest
    ): Flow<Resource<KaboorGenericResponse>> = flow {
        enqueue(
            id,
            body,
            error::convertGenericError,
            api::updatePayment,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun getPaymentDetail(
        id: Int
    ): Flow<Resource<ResponseWrapper<PaymentDetailResponse>>> = flow {
        enqueue(
            id,
            error::convertGenericError,
            api::getPaymentDetail,
            onEmit = { data -> emit(data) }
        )
    }

    override suspend fun getBookingStatus(
        id: Int
    ): Flow<Resource<ResponseWrapper<BookingStatusResponse>>> = flow {
        enqueue(
            id,
            error::convertGenericError,
            api::getBookingStatus,
            onEmit = { data -> emit(data) }
        )
    }
}