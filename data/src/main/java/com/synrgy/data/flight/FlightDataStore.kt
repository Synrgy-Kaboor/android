package com.synrgy.data.flight

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.utils.ext.flowDispatcherIO
import com.synrgy.data.db.KaboorDatabase
import com.synrgy.data.flight.local.AirportEntity
import com.synrgy.data.flight.model.request.FlightRequest
import com.synrgy.data.flight.model.response.AirportResponse
import com.synrgy.data.flight.model.response.FlightResponse
import com.synrgy.data.flight.remote.FlightService
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


class FlightDataStore(
    private val db: KaboorDatabase,
    private val api: FlightService,
    private val error: ErrorParser
) : FlightRepository {

    override suspend fun saveAirport(airports: List<AirportEntity>) {
        db.flightDao().saveList(airports)
    }

    override fun getLocalAirports(): Flow<List<AirportEntity>> {
        return db.flightDao().getList()
    }

    override suspend fun getAirports(): Flow<Resource<ListWrapper<AirportResponse>>> =
        flow {
            enqueue(
                error::convertGenericError,
                api::getAirports,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun getFlights(
        body: FlightRequest
    ): Flow<Resource<ListWrapper<FlightResponse>>> = flow {
        enqueue(
            body.toMap(),
            error::convertGenericError,
            api::getFlights,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

}