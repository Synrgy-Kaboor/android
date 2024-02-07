package com.synrgy.domain.booking

import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.booking.model.request.ProofParam
import com.synrgy.domain.booking.model.request.UpdateProofParam
import com.synrgy.domain.booking.model.response.Booking
import com.synrgy.domain.booking.model.response.BookingStatus
import com.synrgy.domain.booking.model.response.PaymentDetail
import com.synrgy.domain.booking.model.response.UploadProof
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

    suspend fun getPaymentDetail(
        id: Int
    ): Flow<Resource<PaymentDetail>>

    suspend fun getBookingStatus(
        id: Int
    ): Flow<Resource<BookingStatus>>

    suspend fun uploadProof(
        body: ProofParam
    ): Flow<Resource<UploadProof>>

    suspend fun updateProof(
        id: Int,
        body: UpdateProofParam
    ): Flow<Resource<KaboorResponse>>
}