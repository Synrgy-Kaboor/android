package com.synrgy.data.auth

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.auth.model.request.EmailRequest
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.OtpRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.auth.remote.AuthService
import com.synrgy.data.user.model.response.UserResponse
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


class AuthDataStore(
    private val api: AuthService,
    private val error: ErrorParser
) : AuthRepository {

    override suspend fun resendOTP(body: EmailRequest): Flow<Resource<ResponseWrapper<UserResponse>>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::resendOTP,
                onEmit = { data -> emit(data) })
        }.flowOn(Dispatchers.IO)

    override suspend fun verifiedOTP(body: OtpRequest): Flow<Resource<ResponseWrapper<UserResponse>>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::verifiedOTP,
                onEmit = { data -> emit(data) })
        }.flowOn(Dispatchers.IO)

    override suspend fun login(body: LoginRequest): Flow<Resource<ResponseWrapper<LoginResponse>>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::login,
                onEmit = { data -> emit(data) })
        }.flowOn(Dispatchers.IO)

    override suspend fun register(body: RegisterRequest): Flow<Resource<ResponseWrapper<UserResponse>>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::register,
                onEmit = { data -> emit(data) })
        }.flowOn(Dispatchers.IO)

    override suspend fun forgetPassword(body: EmailRequest): Flow<Resource<KaboorGenericResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::forgetPassword,
                onEmit = { data -> emit(data) }
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun verifyOtpResetPassword(body: EmailRequest): Flow<Resource<KaboorGenericResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::verifyOtpResetPassword,
                onEmit = { data -> emit(data) }
            )
        }.flowOn(Dispatchers.IO)

    override suspend fun changePassword(
        body: NewPasswordRequest
    ): Flow<Resource<KaboorGenericResponse>> = flow {
        enqueue(
            body,
            error::convertGenericError,
            api::changePassword,
            onEmit = { data -> emit(data) }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun checkEmail(body: EmailRequest): Flow<Resource<KaboorGenericResponse>> = flow {
        enqueue(
            body,
            error::convertGenericError,
            api::checkEmail,
            onEmit = { data -> emit(data) }
        )
    }.flowOn(Dispatchers.IO)
}