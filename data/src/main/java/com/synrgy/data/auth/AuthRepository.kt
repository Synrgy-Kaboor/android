package com.synrgy.data.auth

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
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


interface AuthRepository {

    suspend fun resendOTP(body: EmailRequest): Flow<Resource<KaboorResponse>>
    suspend fun verifiedOTP(body: OtpRequest): Flow<Resource<KaboorResponse>>
    suspend fun login(body: LoginRequest): Flow<Resource<ResponseWrapper<LoginResponse>>>
    suspend fun register(body: RegisterRequest): Flow<Resource<KaboorResponse>>
    suspend fun forgetPassword(body: EmailRequest): Flow<Resource<KaboorResponse>>
    suspend fun verifyOtpResetPassword(body: OtpRequest): Flow<Resource<KaboorResponse>>
    suspend fun changePassword(body: NewPasswordRequest): Flow<Resource<KaboorResponse>>
    suspend fun resendOtpPassword(body: EmailRequest): Flow<Resource<KaboorResponse>>
    suspend fun checkEmail(body: EmailRequest): Flow<Resource<KaboorResponse>>

    suspend fun changeEmail(body: EmailRequest): Flow<Resource<KaboorResponse>>
    suspend fun verifyOtpEmail(body: OtpRequest): Flow<Resource<KaboorResponse>>
    suspend fun changeNumber(body: PhoneRequest): Flow<Resource<KaboorResponse>>
    suspend fun verifyOtpNumber(body: OtpRequest): Flow<Resource<KaboorResponse>>
    suspend fun resendOtpNumber(): Flow<Resource<KaboorResponse>>
    suspend fun resendOtpEmail(): Flow<Resource<KaboorResponse>>
}