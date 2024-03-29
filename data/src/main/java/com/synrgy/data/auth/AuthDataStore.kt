package com.synrgy.data.auth

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.common.utils.ext.flowDispatcherIO
import com.synrgy.data.auth.model.request.EmailRequest
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.OtpRequest
import com.synrgy.data.auth.model.request.PhoneRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.auth.remote.AuthService
import com.synrgy.data.auth.remote.LoginService
import com.synrgy.data.user.model.response.UserResponse
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


class AuthDataStore(
    private val api: AuthService,
    private val login: LoginService,
    private val error: ErrorParser
) : AuthRepository {

    override suspend fun resendOTP(body: EmailRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::resendOTP,
                onEmit = { data -> emit(data) })
        }.flowDispatcherIO()

    override suspend fun verifiedOTP(body: OtpRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::verifiedOTP,
                onEmit = { data -> emit(data) })
        }.flowDispatcherIO()

    override suspend fun login(body: LoginRequest): Flow<Resource<ResponseWrapper<LoginResponse>>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                login::login,
                onEmit = { data -> emit(data) })
        }.flowDispatcherIO()

    override suspend fun register(body: RegisterRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::register,
                onEmit = { data -> emit(data) })
        }.flowDispatcherIO()

    override suspend fun forgetPassword(body: EmailRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::forgetPassword,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun verifyOtpResetPassword(body: OtpRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::verifyOtpResetPassword,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun changePassword(
        body: NewPasswordRequest
    ): Flow<Resource<KaboorResponse>> = flow {
        enqueue(
            body,
            error::convertGenericError,
            api::changePassword,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

    override suspend fun resendOtpPassword(body: EmailRequest): Flow<Resource<KaboorResponse>> = flow{
        enqueue(
            body,
            error::convertGenericError,
            api::resendOtpResetPassword,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

    override suspend fun checkEmail(body: EmailRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                login::checkEmail,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun changeEmail(body: EmailRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::changeEmail,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun verifyOtpEmail(body: OtpRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::verifyOtpEmail,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun changeNumber(body: PhoneRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::changeNumber,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun verifyOtpNumber(body: OtpRequest): Flow<Resource<KaboorResponse>> =
        flow {
            enqueue(
                body,
                error::convertGenericError,
                api::verifyOtpNumber,
                onEmit = { data -> emit(data) }
            )
        }.flowDispatcherIO()

    override suspend fun resendOtpNumber(): Flow<Resource<KaboorResponse>> = flow<Resource<KaboorResponse>> {
        enqueue(
            error::convertGenericError,
            api::resendOtpNumber,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

    override suspend fun resendOtpEmail(): Flow<Resource<KaboorResponse>> = flow<Resource<KaboorResponse>> {
        enqueue(
            error::convertGenericError,
            api::resendOtpEmail,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()
}