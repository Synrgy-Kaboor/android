package com.synrgy.di.features

import com.synrgy.data.user.UserDataStore
import com.synrgy.data.user.UserRepository
import com.synrgy.data.user.remote.UserService
import com.synrgy.domain.user.UserInteractor
import com.synrgy.domain.user.UserUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


val userModule = module {
    single { get<Retrofit>().create(UserService::class.java) }
    single<UserRepository> { UserDataStore(get(), get(), get()) }
    single<UserUseCase> { UserInteractor(get()) }
}