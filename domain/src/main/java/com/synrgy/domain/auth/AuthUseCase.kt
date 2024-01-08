package com.synrgy.domain.auth

import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.domain.auth.model.response.Login
import com.synrgy.domain.auth.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


interface AuthUseCase {
    suspend fun resendOTP(email: String): Flow<Resource<User>>
    suspend fun verifiedOTP(otp: String): Flow<Resource<User>>
    suspend fun login(data: LoginRequest): Flow<Resource<Login>>
    suspend fun register(data: RegisterRequest): Flow<Resource<User>>
    suspend fun forgetPassword(email: String): Flow<Resource<KaboorGenericResponse>>
}