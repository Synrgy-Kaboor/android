package com.synrgy.data.booking.remote

import com.synrgy.common.data.ResponseListWrapper
import com.synrgy.data.booking.model.response.AirportResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


/**
 * Created by wahid on 1/24/2024.
 * Github github.com/wahidabd.
 */


interface BookingService {

    @GET("/api/v1/flights")
    suspend fun getFlights(
        @QueryMap query: Map<String, String>
    )

    @GET(" /api/v1/airports")
    suspend fun getAirports(): Response<ResponseListWrapper<AirportResponse>>
}