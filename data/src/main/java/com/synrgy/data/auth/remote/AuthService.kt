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
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


interface AuthService {

    @POST("/api/v1/auth/register/user/otp/resend")
    suspend fun resendOTP(
        @Body body: EmailRequest
    ): Response<KaboorResponse>


    @POST("/api/v1/auth/register/user/otp/verify")
    suspend fun verifiedOTP(
        @Body body: OtpRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/register/user")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/user/password/forget")
    suspend fun forgetPassword(
        @Body body: EmailRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/user/password/otp/resend")
    suspend fun resendOtpResetPassword(
        @Body body: EmailRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/auth/user/password/otp/verify")
    suspend fun verifyOtpResetPassword(
       @Body body: OtpRequest
    ): Response<KaboorResponse>

    @PATCH("/api/v1/auth/user/password")
    suspend fun changePassword(
        @Body body: NewPasswordRequest
    ): Response<KaboorResponse>

    @POST("/api/v1/user/email/otp/verify")
    suspend fun verifyOtpEmail(
        @Body body: OtpRequest
    ): Response<KaboorResponse>

    @GET("/api/v1/user/email/otp/resend")
    suspend fun resendOtpEmail(
    ): Response<KaboorResponse>

    @PATCH("/api/v1/user/email")
    suspend fun changeEmail(
        @Body body: EmailRequest
    ):Response<KaboorResponse>

    @POST("/api/v1/user/phone/otp/verify")
    suspend fun verifyOtpNumber(
        @Body body: OtpRequest
    ): Response<KaboorResponse>

    @GET("/api/v1/user/phone/otp/resend")
    suspend fun resendOtpNumber(
    ): Response<KaboorResponse>

    @PATCH("/api/v1/user/phone")
    suspend fun changeNumber(
        @Body body: PhoneRequest
    ):Response<KaboorResponse>


}