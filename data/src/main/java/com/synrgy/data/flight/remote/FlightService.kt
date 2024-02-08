package com.synrgy.data.flight.remote

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.data.flight.model.response.FlightResponse
import com.wahidabd.library.data.WebApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


interface FlightService {

    @GET("/api/v1/airport")
    suspend fun getAirports(): Response<ListWrapper<AirportResponse>>

    @GET("/api/v1/flight")
    suspend fun getFlights(
        @QueryMap query: Map<String, String>
    ): Response<ListWrapper<FlightResponse>>

    @GET("/api/v1/flights/{id}")
    suspend fun getFlightById(
        @Path("id") id: Int
    )
}