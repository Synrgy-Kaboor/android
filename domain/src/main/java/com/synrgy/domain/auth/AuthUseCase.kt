package com.synrgy.domain.auth

import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.request.NewPasswordParam
import com.synrgy.domain.auth.model.request.OtpParam
import com.synrgy.domain.auth.model.request.PhoneParam
import com.synrgy.domain.auth.model.request.RegisterParam
import com.synrgy.domain.auth.model.response.Login
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


interface AuthUseCase {
    suspend fun resendOTP(body: EmailParam): Flow<Resource<KaboorGenericResponse>>
    suspend fun verifiedOTP(body: OtpParam): Flow<Resource<User>>
    suspend fun login(body: LoginParam): Flow<Resource<Login>>
    suspend fun register(body: RegisterParam): Flow<Resource<User>>
    suspend fun forgetPassword(body: EmailParam): Flow<Resource<KaboorGenericResponse>>
    suspend fun verifyOtpResetPassword(body: OtpParam): Flow<Resource<KaboorGenericResponse>>
    suspend fun changePassword(body: NewPasswordParam): Flow<Resource<KaboorGenericResponse>>
    suspend fun checkEmail(body: EmailParam): Flow<Resource<KaboorGenericResponse>>
    suspend fun changeEmail(body: EmailParam): Flow<Resource<KaboorGenericResponse>>

    suspend fun verifyOtpChangeEmail(body: OtpParam): Flow<Resource<KaboorGenericResponse>>

    suspend fun changePhone(body: PhoneParam): Flow<Resource<KaboorGenericResponse>>

    suspend fun verifyOtpChangePhone(body: OtpParam): Flow<Resource<KaboorGenericResponse>>
}