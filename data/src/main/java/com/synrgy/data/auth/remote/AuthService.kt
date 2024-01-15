package com.synrgy.data.auth.remote

import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.user.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


interface AuthService {

    @FormUrlEncoded
    @POST("/api/v1/auth/otp/resend")
    suspend fun resendOTP(
        @Field("email") email: String
    ): Response<ResponseWrapper<UserResponse>>

    @FormUrlEncoded
    @POST("/api/v1/auth/otp/verify")
    suspend fun verifiedOTP(
        @Field("otp") otp: String
    ): Response<ResponseWrapper<UserResponse>>

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<ResponseWrapper<LoginResponse>>

    @POST("/api/v1/auth/register/user")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<ResponseWrapper<UserResponse>>

    @FormUrlEncoded
    @POST("/api/v1/auth/password/forget")
    suspend fun forgetPassword(
        @Field("email") email: String
    ): Response<KaboorGenericResponse>

    @FormUrlEncoded
    @POST("/api/v1/auth/password/otp/verify")
    suspend fun verifyOtpResetPassword(
        @Field("email") email: String,
    ): Response<KaboorGenericResponse>

    @POST("/api/v1/auth/password/change")
    suspend fun changePassword(
        @Body body: NewPasswordRequest
    ): Response<KaboorGenericResponse>

    @FormUrlEncoded
    @POST("/api/v1/auth/check/email")
    suspend fun checkEmail(
        @Field("email") email: String
    ): Response<KaboorGenericResponse>
}