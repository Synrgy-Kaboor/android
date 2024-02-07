package com.synrgy.domain.booking

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.booking.BookingRepository
import com.synrgy.data.booking.model.response.BookingResponse
import com.synrgy.data.booking.model.response.BookingStatusResponse
import com.synrgy.data.booking.model.response.PaymentDetailResponse
import com.synrgy.data.booking.model.response.UploadProofResponse
import com.synrgy.domain.booking.mapper.toDomain
import com.synrgy.domain.booking.mapper.toRequest
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.booking.model.request.ProofParam
import com.synrgy.domain.booking.model.request.UpdateProofParam
import com.synrgy.domain.booking.model.response.Booking
import com.synrgy.domain.booking.model.response.BookingStatus
import com.synrgy.domain.booking.model.response.PaymentDetail
import com.synrgy.domain.booking.model.response.UploadProof
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


class BookingInteractor(
    private val bookingRepository: BookingRepository
) : BookingUseCase {

    override suspend fun createBooking(
        body: BookingParam
    ): Flow<Resource<Booking>> {
        return object : InternetBoundResource<Booking, ResponseWrapper<BookingResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<BookingResponse>>> {
                return bookingRepository.createBooking(body.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<BookingResponse>): Booking {
                return data.data.toDomain()
            }
        }.asFlow()
    }

    override suspend fun getPaymentDetail(
        id: Int
    ): Flow<Resource<PaymentDetail>> {
        return object :
            InternetBoundResource<PaymentDetail, ResponseWrapper<PaymentDetailResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<PaymentDetailResponse>>> {
                return bookingRepository.getPaymentDetail(id)
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<PaymentDetailResponse>): PaymentDetail {
                return data.data.toDomain()
            }
        }.asFlow()
    }

    override suspend fun getBookingStatus(
        id: Int
    ): Flow<Resource<BookingStatus>> {
        return object :
            InternetBoundResource<BookingStatus, ResponseWrapper<BookingStatusResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<BookingStatusResponse>>> {
                return bookingRepository.getBookingStatus(id)
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<BookingStatusResponse>): BookingStatus {
                return data.data.toDomain()
            }
        }.asFlow()
    }

    override suspend fun uploadProof(body: ProofParam): Flow<Resource<UploadProof>> {
        return object : InternetBoundResource<UploadProof, ResponseWrapper<UploadProofResponse>>(){
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<UploadProofResponse>>> {
                return bookingRepository.uploadProof(body.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<UploadProofResponse>): UploadProof {
                return data.data.toDomain()
            }
        }.asFlow()
    }

    override suspend fun updateProof(
        id: Int,
        body: UpdateProofParam
    ): Flow<Resource<KaboorResponse>> {
        return bookingRepository.updateProof(id, body.toRequest())
    }
}