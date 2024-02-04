package com.synrgy.data.flight

import com.synrgy.common.data.ResponseListWrapper
import com.synrgy.data.flight.local.AirportEntity
import com.synrgy.data.flight.model.response.AirportResponse
import com.wahidabd.library.data.BaseRepository
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


interface FlightRepository  {
    suspend fun saveAirport(airports: List<AirportEntity>)
    fun getLocalAirports(): Flow<List<AirportEntity>>
    suspend fun getAirports(): Flow<Resource<ResponseListWrapper<AirportResponse>>>

}