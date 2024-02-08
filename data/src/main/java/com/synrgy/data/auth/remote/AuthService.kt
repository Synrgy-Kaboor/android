package com.synrgy.data.auth.remote

import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.data.auth.model.request.EmailRequest
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.OtpRequest
import com.synrgy.data.auth.model.request.PhoneRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.user.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


interface AuthService {

    @POST("/api/v1/auth/otp/resend")
    suspend fun resendOTP(
        @Body body: EmailRequest
    ): Response<KaboorResponse>


    @POST("/api/v1/auth/otp/verify")
    suspend fun verifiedOTP(
        @Body body: OtpRequest
    ): Response<ResponseWrapper<UserResponse>>

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<ResponseWrapper<LoginResponse>>

    @POST("/api/v1/auth/register/user")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<ResponseWrapper<UserResponse>>

    @POST("/api/v1/auth/password/forget")
    suspend fun forgetPassword(
        @Body body: EmailRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/password/otp/verify")
    suspend fun verifyOtpResetPassword(
       @Body body: OtpRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/password/change")
    suspend fun changePassword(
        @Body body: NewPasswordRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/check/email")
    suspend fun checkEmail(
        @Body body: EmailRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/change/email")
    suspend fun changeEmail(
        @Body body: EmailRequest
    ):Response<KaboorResponse>

    @POST("/api/v1/auth/email/otp/verify")
    suspend fun verifyOtpEmail(
        @Body body: OtpRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/change/number")
    suspend fun changeNumber(
        @Body body: PhoneRequest
    ):Response<KaboorResponse>

    @POST("/api/v1/auth/number/otp/verify")
    suspend fun verifyOtpNumber(
        @Body body: OtpRequest
    ): Response<KaboorResponse>

}