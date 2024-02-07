package com.synrgy.data.booking

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.utils.ext.flowDispatcherIO
import com.synrgy.data.booking.model.request.BookingRequest
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
    }.flowDispatcherIO()

    override suspend fun getPaymentDetail(
        id: Int
    ): Flow<Resource<ResponseWrapper<PaymentDetailResponse>>> = flow {
        enqueue(
            id,
            error::convertGenericError,
            api::getPaymentDetail,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

    override suspend fun getBookingStatus(
        id: Int
    ): Flow<Resource<ResponseWrapper<BookingStatusResponse>>> = flow {
        enqueue(
            id,
            error::convertGenericError,
            api::getBookingStatus,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()
}