package com.synrgy.di.features

import com.synrgy.data.auth.remote.AuthService
import com.synrgy.data.flight.FlightDataStore
import com.synrgy.data.flight.FlightRepository
import com.synrgy.data.flight.remote.FlightService
import com.synrgy.domain.flight.FlightInteractor
import com.synrgy.domain.flight.FlightUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


val flightModule = module {
    single { get<Retrofit>().create(FlightService::class.java) }
    single<FlightRepository> { FlightDataStore(get(), get(), get()) }
    single<FlightUseCase> { FlightInteractor(get()) }
}