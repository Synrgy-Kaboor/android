package com.synrgy.data.auth

import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
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

    suspend fun resendOTP(email: String): Flow<Resource<ResponseWrapper<UserResponse>>>
    suspend fun verifiedOTP(otp: String): Flow<Resource<ResponseWrapper<UserResponse>>>
    suspend fun login(data: LoginRequest): Flow<Resource<ResponseWrapper<LoginResponse>>>
    suspend fun register(data: RegisterRequest): Flow<Resource<ResponseWrapper<UserResponse>>>
    suspend fun forgetPassword(email: String): Flow<Resource<KaboorGenericResponse>>
    suspend fun verifyOtpResetPassword(email: String): Flow<Resource<KaboorGenericResponse>>
    suspend fun changePassword(data: NewPasswordRequest): Flow<Resource<KaboorGenericResponse>>
    suspend fun checkEmail(email: String): Flow<Resource<KaboorGenericResponse>>
}