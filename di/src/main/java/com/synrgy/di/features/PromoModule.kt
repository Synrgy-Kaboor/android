package com.synrgy.di.features

import com.synrgy.data.promo.PromoDataStore
import com.synrgy.data.promo.PromoRepository
import com.synrgy.domain.promo.PromoInteractor
import com.synrgy.domain.promo.PromoUseCase
import org.koin.dsl.module


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


val promoModule = module {
    single<PromoRepository> { PromoDataStore(get(), get()) }
    single<PromoUseCase> { PromoInteractor(get()) }
}