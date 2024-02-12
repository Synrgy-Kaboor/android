package com.synrgy.di.features

import com.synrgy.data.order.OrderDataStore
import com.synrgy.data.order.OrderRepository
import com.synrgy.domain.order.OrderInteractor
import com.synrgy.domain.order.OrderUseCase
import org.koin.dsl.module


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


val orderModule = module {
    single<OrderRepository> { OrderDataStore(get(), get()) }
    single<OrderUseCase> { OrderInteractor(get()) }
}