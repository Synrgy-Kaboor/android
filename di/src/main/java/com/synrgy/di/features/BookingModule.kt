package com.synrgy.di.features

import com.synrgy.data.auth.remote.AuthService
import com.synrgy.data.booking.BookingDataStore
import com.synrgy.data.booking.BookingRepository
import com.synrgy.data.booking.remote.BookingService
import com.synrgy.domain.booking.BookingInteractor
import com.synrgy.domain.booking.BookingUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


val bookingModule = module {
    single { get<Retrofit>().create(BookingService::class.java) }
    single<BookingRepository> { BookingDataStore(get(), get()) }
    single<BookingUseCase> { BookingInteractor(get()) }
}