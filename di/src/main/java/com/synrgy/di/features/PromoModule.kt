package com.synrgy.di.features

import com.synrgy.data.auth.remote.AuthService
import com.synrgy.data.promo.PromoDataStore
import com.synrgy.data.promo.PromoRepository
import com.synrgy.data.promo.remote.PromoService
import com.synrgy.domain.promo.PromoInteractor
import com.synrgy.domain.promo.PromoUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


val promoModule = module {
    single { get<Retrofit>().create(PromoService::class.java) }
    single<PromoRepository> { PromoDataStore(get(), get()) }
    single<PromoUseCase> { PromoInteractor(get()) }
}