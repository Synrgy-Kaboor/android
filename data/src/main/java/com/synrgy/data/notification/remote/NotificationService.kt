package com.synrgy.data.notification.remote

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.notification.model.request.PriceNotificationRequest
import com.synrgy.data.notification.model.response.PriceNotificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


interface NotificationService {

    @GET("/api/v1/user/notification/price")
    suspend fun getPriceNotification(): Response<ResponseWrapper<PriceNotificationResponse>>

    @POST("/api/v1/user/notification/price")
    suspend fun createPriceNotification(
        @Body body: PriceNotificationRequest,
    ): Response<KaboorResponse>

    @PATCH("/api/v1/user/notification/price/{id}")
    suspend fun updatePriceNotification(
        @Path("id") id: Int,
        @Body body: PriceNotificationRequest,
    ): Response<KaboorResponse>

    @DELETE("/api/v1/user/notification/price/{id}")
    suspend fun deletePriceNotification(
        @Path("id") id: Int,
    ): Response<KaboorResponse>
}
