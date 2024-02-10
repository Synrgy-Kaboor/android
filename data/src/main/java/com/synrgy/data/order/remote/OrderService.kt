package com.synrgy.data.order.remote

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.order.model.response.OrderResponse
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


interface OrderService {

    @GET("/api/v1/booking/active")
    suspend fun getActive(): Response<ListWrapper<OrderResponse>>

    @GET("/api/v1/booking/finished")
    suspend fun getFinished(): Response<ListWrapper<OrderResponse>>
}