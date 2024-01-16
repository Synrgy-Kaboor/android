package com.synrgy.di.features

import com.synrgy.data.auth.AuthDataStore
import com.synrgy.data.auth.AuthRepository
import com.synrgy.data.auth.remote.AuthService
import com.synrgy.domain.auth.AuthInteractor
import com.synrgy.domain.auth.AuthUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


val authModule = module {
    single { get<Retrofit>().create(AuthService::class.java) }
    single<AuthRepository> { AuthDataStore(get(), get()) }
    single<AuthUseCase> { AuthInteractor(get()) }
}