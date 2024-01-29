package com.synrgy.data.booking.remote

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

    @GET("/api/v1/booking/{id}")
    suspend fun getBookingInfo(
        @Path("id") id: Int
    ): Response<ResponseWrapper<BookingInfoResponse>>

    @PATCH("/api/v1/booking/{id}/ordererDetails")
    suspend fun updateBooking(
        @Path("id") id: Int,
        @Body body: UpdateBookingRequest
    ): Response<KaboorGenericResponse>

    @PATCH("/api/v1/booking/{id}/additionalService")
    suspend fun updateAdditionalService(
        @Path("id") id: Int,
        @Body body: UpdateAdditionalBookingRequest
    ): Response<KaboorGenericResponse>

    @PATCH("/api/v1/booking/{id}/payment")
    suspend fun updatePayment(
        @Path("id") id: Int,
        @Body body: UpdatePaymentMethodRequest
    ): Response<KaboorGenericResponse>

    @GET("/api/v1/booking/{id}/payment")
    suspend fun getPaymentDetail(
        @Path("id") id: Int
    ): Response<ResponseWrapper<PaymentDetailResponse>>

    @GET("/api/v1/booking/{id}/status")
    suspend fun getBookingStatus(
        @Path("id") id: Int
    ): Response<ResponseWrapper<BookingStatusResponse>>
}