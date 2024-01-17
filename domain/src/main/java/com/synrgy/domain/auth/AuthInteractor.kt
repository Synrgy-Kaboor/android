package com.synrgy.domain.auth

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.auth.AuthRepository
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.user.model.response.UserResponse
import com.synrgy.domain.auth.mapper.toDomain
import com.synrgy.domain.auth.mapper.toRequest
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.request.NewPasswordParam
import com.synrgy.domain.auth.model.request.OtpParam
import com.synrgy.domain.auth.model.request.RegisterParam
import com.synrgy.domain.auth.model.response.Login
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


class AuthInteractor(private val repository: AuthRepository) : AuthUseCase {

    override suspend fun resendOTP(body: EmailParam): Flow<Resource<User>> {
        return object : InternetBoundResource<User, ResponseWrapper<UserResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<UserResponse>>> {
                return repository.resendOTP(body.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<UserResponse>): User {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun verifiedOTP(body: OtpParam): Flow<Resource<User>> {
        return object : InternetBoundResource<User, ResponseWrapper<UserResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<UserResponse>>> {
                return repository.verifiedOTP(body.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<UserResponse>): User {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun login(body: LoginParam): Flow<Resource<Login>> {
        return object : InternetBoundResource<Login, ResponseWrapper<LoginResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<LoginResponse>>> {
                return repository.login(body.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<LoginResponse>): Login {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun register(body: RegisterParam): Flow<Resource<User>> {
        return object : InternetBoundResource<User, ResponseWrapper<UserResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<UserResponse>>> {
                return repository.register(body.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<UserResponse>): User {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun forgetPassword(body: EmailParam): Flow<Resource<KaboorGenericResponse>> {
        return repository.forgetPassword(body.toRequest())
    }

    override suspend fun verifyOtpResetPassword(body: EmailParam): Flow<Resource<KaboorGenericResponse>> {
        return repository.verifyOtpResetPassword(body.toRequest())
    }

    override suspend fun changePassword(body: NewPasswordParam): Flow<Resource<KaboorGenericResponse>> {
        return repository.changePassword(body.toRequest())
    }

    override suspend fun checkEmail(body: EmailParam): Flow<Resource<KaboorGenericResponse>> {
        return repository.checkEmail(body.toRequest())
    }
}