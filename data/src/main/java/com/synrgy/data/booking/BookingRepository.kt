package com.synrgy.data.booking

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.booking.model.request.BookingRequest
import com.synrgy.data.booking.model.request.ProofRequest
import com.synrgy.data.booking.model.request.UpdateProofRequest
import com.synrgy.data.booking.model.response.BookingResponse
import com.synrgy.data.booking.model.response.BookingStatusResponse
import com.synrgy.data.booking.model.response.PaymentDetailResponse
import com.synrgy.data.booking.model.response.UploadProofResponse
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

    suspend fun getPaymentDetail(
        id: Int
    ): Flow<Resource<ResponseWrapper<PaymentDetailResponse>>>

    suspend fun getBookingStatus(
        id: Int
    ): Flow<Resource<ResponseWrapper<BookingStatusResponse>>>

    suspend fun uploadProof(
        body: ProofRequest
    ): Flow<Resource<ResponseWrapper<UploadProofResponse>>>

    suspend fun updateProof(
        id: Int,
        body: UpdateProofRequest
    ): Flow<Resource<KaboorResponse>>
}