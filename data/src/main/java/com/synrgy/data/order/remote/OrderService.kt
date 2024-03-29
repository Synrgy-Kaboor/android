package com.synrgy.data.order.remote

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.data.order.model.response.OrderResponse
import com.synrgy.data.order.model.response.TicketDetailResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


interface OrderService {

    @GET("/api/v1/booking/active")
    suspend fun getActive(): Response<ListWrapper<OrderResponse>>

    @GET("/api/v1/booking/finished")
    suspend fun getFinished(): Response<ListWrapper<OrderResponse>>

    @GET("/api/v1/booking/{id}/outbound")
    suspend fun getOutbound(
        @Path("id") id: Int
    ): Response<ResponseWrapper<TicketDetailResponse>>

    @GET("/api/v1/booking/{id}/return")
    suspend fun getReturn(
        @Path("id") id: Int
    ): Response<ResponseWrapper<TicketDetailResponse>>

    @GET("/api/v1/booking/{id}/outbound/ticket")
    suspend fun downloadOutboundTicket(
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET("/api/v1/booking/{id}/return/ticket")
    suspend fun downloadReturnTicket(
        @Path("id") id: Int
    ): Response<ResponseBody>
}