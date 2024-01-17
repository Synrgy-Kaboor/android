package com.synrgy.data.auth

import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.data.auth.model.request.EmailRequest
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.OtpRequest
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

    suspend fun resendOTP(body: EmailRequest): Flow<Resource<KaboorGenericResponse>>
    suspend fun verifiedOTP(body: OtpRequest): Flow<Resource<ResponseWrapper<UserResponse>>>
    suspend fun login(body: LoginRequest): Flow<Resource<ResponseWrapper<LoginResponse>>>
    suspend fun register(body: RegisterRequest): Flow<Resource<ResponseWrapper<UserResponse>>>
    suspend fun forgetPassword(body: EmailRequest): Flow<Resource<KaboorGenericResponse>>
    suspend fun verifyOtpResetPassword(body: OtpRequest): Flow<Resource<KaboorGenericResponse>>
    suspend fun changePassword(body: NewPasswordRequest): Flow<Resource<KaboorGenericResponse>>
    suspend fun checkEmail(body: EmailRequest): Flow<Resource<KaboorGenericResponse>>
}