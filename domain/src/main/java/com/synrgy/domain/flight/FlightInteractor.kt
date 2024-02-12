package com.synrgy.domain.flight

import com.synrgy.common.data.ListWrapper
import com.synrgy.data.flight.FlightRepository
import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.data.flight.model.response.FlightResponse
import com.synrgy.domain.flight.mapper.toDomain
import com.synrgy.domain.flight.mapper.toEntity
import com.synrgy.domain.flight.mapper.toRequest
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Airport
import com.synrgy.domain.flight.model.response.Flight
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import com.wahidabd.library.utils.coroutine.boundResource.NetworkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


class FlightInteractor(
    private val repository: FlightRepository
) : FlightUseCase {

    override suspend fun getAirports(): Flow<Resource<List<Airport>>> {
        return object :
            NetworkBoundResource<List<Airport>, ListWrapper<AirportResponse>>() {
            override suspend fun createCall(): Flow<Resource<ListWrapper<AirportResponse>>> {
                return repository.getAirports()
            }

            override fun loadFromDB(): Flow<List<Airport>> {
                return repository.getLocalAirports().map { data ->
                    data.map { it.toDomain() }
                }
            }

            override fun shouldFetch(data: List<Airport>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun saveCallRequest(data: ListWrapper<AirportResponse>) {
                repository.saveAirport(data.data.map { it.toEntity() })
            }

        }.asFlow()
    }

    override suspend fun getFlights(body: FlightParam): Flow<Resource<List<Flight>>> {
        return object : InternetBoundResource<List<Flight>, ListWrapper<FlightResponse>>() {
            override suspend fun createCall(): Flow<Resource<ListWrapper<FlightResponse>>> {
                return repository.getFlights(body.toRequest())
            }

            override suspend fun saveCallRequest(data: ListWrapper<FlightResponse>): List<Flight> {
                return data.data.map { it.toDomain() }
            }
        }.asFlow()
    }

}