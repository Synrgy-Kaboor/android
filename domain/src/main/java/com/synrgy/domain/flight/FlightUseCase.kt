package com.synrgy.domain.flight

import com.synrgy.common.data.ResponseListWrapper
import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Airport
import com.synrgy.domain.flight.model.response.Flight
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


interface FlightUseCase {

    suspend fun getAirports(): Flow<Resource<List<Airport>>>
    suspend fun getFlights(body: FlightParam): Flow<Resource<List<Flight>>>
}