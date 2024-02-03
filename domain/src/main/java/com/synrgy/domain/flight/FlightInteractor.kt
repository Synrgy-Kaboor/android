package com.synrgy.domain.flight

import com.synrgy.common.data.ResponseListWrapper
import com.synrgy.data.flight.FlightRepository
import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.domain.flight.mapper.toDomain
import com.synrgy.domain.flight.mapper.toEntity
import com.synrgy.domain.flight.model.response.Airport
import com.wahidabd.library.data.Resource
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
            NetworkBoundResource<List<Airport>, ResponseListWrapper<AirportResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseListWrapper<AirportResponse>>> {
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

            override suspend fun saveCallRequest(data: ResponseListWrapper<AirportResponse>) {
                repository.saveAirport(data.data.map { it.toEntity() })
            }

        }.asFlow()
    }

}