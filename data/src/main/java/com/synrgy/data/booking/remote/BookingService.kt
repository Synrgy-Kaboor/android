package com.synrgy.data.booking.remote

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.booking.model.request.BookingRequest
import com.synrgy.data.booking.model.request.UpdateProofRequest
import com.synrgy.data.booking.model.response.BookingResponse
import com.synrgy.data.booking.model.response.BookingStatusResponse
import com.synrgy.data.booking.model.response.PaymentDetailResponse
import com.synrgy.data.booking.model.response.UploadProofResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


interface BookingService {
    @POST("/api/v1/booking")
    suspend fun createBooking(
        @Body body: BookingRequest
    ): Response<ResponseWrapper<BookingResponse>>

    @GET("/api/v1/booking/{id}/payment")
    suspend fun getPaymentDetail(
        @Path("id") id: Int
    ): Response<ResponseWrapper<PaymentDetailResponse>>

    @GET("/api/v1/booking/{id}/status")
    suspend fun getBookingStatus(
        @Path("id") id: Int
    ): Response<ResponseWrapper<BookingStatusResponse>>

    @POST("/api/v1/booking/payment/file")
    suspend fun uploadProof(
        @Body body: MultipartBody
    ): Response<ResponseWrapper<UploadProofResponse>>

    @PATCH("/api/v1/booking/{id}/payment/proof")
    suspend fun updateProof(
        @Path("id") id: Int,
        @Body body: UpdateProofRequest
    ): Response<KaboorResponse>
}