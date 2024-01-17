package com.synrgy.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.synrgy.data.user.local.KaboorDataStore
import com.wahidabd.library.data.libs.OkHttpClientFactory
import com.wahidabd.library.data.libs.interceptor.HeaderInterceptor
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 * Created by wahid on 12/27/2023.
 * Github github.com/wahidabd.
 */


const val BASE_URL = "base_url"

val appModule = module {

    single { KaboorDataStore.getInstance(get()) }

    single {
        return@single OkHttpClientFactory.create(
            interceptors = listOf(
                getHeaderInterceptor(get()),
                chuckerInterceptor(get())
            ),
            showDebugLog = BuildConfig.DEBUG,
            authenticator = null,
            certificatePinner = null
        )
    }

    single(named(BASE_URL)) { BuildConfig.base_url }
}

private fun getHeaderInterceptor(data: KaboorDataStore): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"
    if (data.getToken()?.isNotEmpty() == true){
        headers["Authorization"] = "Bearer ${data.getToken()}"
    }
    return HeaderInterceptor(headers)
}

private fun chuckerInterceptor(context: Context): Interceptor {
    return ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(true)
        .build()
}