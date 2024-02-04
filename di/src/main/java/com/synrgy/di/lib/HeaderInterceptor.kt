package com.synrgy.di.lib

import com.synrgy.data.db.KaboorDataStore
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


class HeaderInterceptor(
    private val user: KaboorDataStore
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = mapHeaders(chain)
        return chain.proceed(request)
    }

    private fun mapHeaders(chain: Interceptor.Chain): Request {
        val original =  chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.addHeader("Authorization", getAccessToken())
        requestBuilder.addHeader("Content-Type", "application/json")

        return requestBuilder.build()
    }

    private fun getAccessToken(): String {
        val token = user.getToken()
        return if (token?.isNotEmpty() == true) "Bearer $token" else ""
    }


}