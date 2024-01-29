package com.synrgy.data.flight.remote

import com.synrgy.common.data.ResponseListWrapper
import com.synrgy.data.flight.model.response.AirportResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


interface FlightService {

    @GET("/api/v1/airports")
    suspend fun getAirports(): Response<ResponseListWrapper<AirportResponse>>

    @GET("/api/v1/flights")
    suspend fun getFlights(
        @QueryMap query: Map<String, String>
    )

    @GET("/api/v1/flights/{id}")
    suspend fun getFlightById(
        @Path("id") id: Int
    )
}