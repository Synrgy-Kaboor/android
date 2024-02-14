package com.synrgy.data.auth.remote

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.auth.model.request.EmailRequest
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Created by wahid on 2/14/2024.
 * Github github.com/wahidabd.
 */


interface LoginService {

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<ResponseWrapper<LoginResponse>>

    @POST("/api/v1/auth/check/email")
    suspend fun checkEmail(
        @Body body: EmailRequest
    ): Response<KaboorResponse>

}