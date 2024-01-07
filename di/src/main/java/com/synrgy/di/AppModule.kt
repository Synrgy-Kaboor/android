package com.synrgy.di

import com.wahidabd.library.data.libs.OkHttpClientFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


const val BASE_URL = "base_url"

val appModule = module {
    single {
        return@single OkHttpClientFactory.create(
            interceptors = listOf(),
            showDebugLog = BuildConfig.DEBUG,
            authenticator = null,
            certificatePinner = null
        )
    }

    single(named(BASE_URL)){BuildConfig.base_url}
}