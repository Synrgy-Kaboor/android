package com.synrgy.di

import com.wahidabd.library.data.libs.ApiService
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


val retrofitModule = module {
    single {
        ApiService.createService(
            get(), get(named(BASE_URL))
        )
    }

    factory { ErrorParser(get()) }
}