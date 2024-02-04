package com.synrgy.di.features

import com.synrgy.data.flight.FlightDataStore
import com.synrgy.data.flight.FlightRepository
import com.synrgy.domain.flight.FlightInteractor
import com.synrgy.domain.flight.FlightUseCase
import org.koin.dsl.module


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


val flightModule = module {
    single<FlightRepository> { FlightDataStore(get(), get(), get()) }
    single<FlightUseCase> { FlightInteractor(get()) }
}