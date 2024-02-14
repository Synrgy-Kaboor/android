package com.synrgy.domain.auth

import com.synrgy.common.data.response.KaboorResponse
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


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


interface AuthUseCase {
    suspend fun resendOTP(body: EmailParam): Flow<Resource<KaboorResponse>>
    suspend fun verifiedOTP(body: OtpParam): Flow<Resource<KaboorResponse>>
    suspend fun login(body: LoginParam): Flow<Resource<Login>>
    suspend fun register(body: RegisterParam): Flow<Resource<KaboorResponse>>
    suspend fun forgetPassword(body: EmailParam): Flow<Resource<KaboorResponse>>
    suspend fun verifyOtpResetPassword(body: OtpParam): Flow<Resource<KaboorResponse>>
    suspend fun resendOtpPassword(body: EmailParam): Flow<Resource<KaboorResponse>>
    suspend fun changePassword(body: NewPasswordParam): Flow<Resource<KaboorResponse>>
    suspend fun checkEmail(body: EmailParam): Flow<Resource<KaboorResponse>>
    suspend fun changeEmail(body: EmailParam): Flow<Resource<KaboorResponse>>
    suspend fun verifyOtpChangeEmail(body: OtpParam): Flow<Resource<KaboorResponse>>
    suspend fun changePhone(body: PhoneParam): Flow<Resource<KaboorResponse>>
    suspend fun verifyOtpChangePhone(body: OtpParam): Flow<Resource<KaboorResponse>>
    suspend fun resendOtpNumber(): Flow<Resource<KaboorResponse>>
    suspend fun resendOtpEmail(): Flow<Resource<KaboorResponse>>
}