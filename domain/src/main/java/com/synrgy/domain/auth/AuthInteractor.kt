package com.synrgy.domain.auth

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.auth.AuthRepository
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.user.model.response.UserResponse
import com.synrgy.domain.auth.mapper.toDomain
import com.synrgy.domain.auth.mapper.toRequest
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.request.NewPasswordParam
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

    override suspend fun resendOTP(email: String): Flow<Resource<User>> {
        return object : InternetBoundResource<User, ResponseWrapper<UserResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<UserResponse>>> {
                return repository.resendOTP(email)
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<UserResponse>): User {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun verifiedOTP(otp: String): Flow<Resource<User>> {
        return object : InternetBoundResource<User, ResponseWrapper<UserResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<UserResponse>>> {
                return repository.verifiedOTP(otp)
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<UserResponse>): User {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun login(data: LoginParam): Flow<Resource<Login>> {
        return object : InternetBoundResource<Login, ResponseWrapper<LoginResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<LoginResponse>>> {
                return repository.login(data.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<LoginResponse>): Login {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun register(data: RegisterParam): Flow<Resource<User>> {
        return object : InternetBoundResource<User, ResponseWrapper<UserResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<UserResponse>>> {
                return repository.register(data.toRequest())
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<UserResponse>): User {
                return data.data.toDomain()
            }

        }.asFlow()
    }

    override suspend fun forgetPassword(email: String): Flow<Resource<KaboorGenericResponse>> {
        return repository.forgetPassword(email)
    }

    override suspend fun verifyOtpResetPassword(email: String): Flow<Resource<KaboorGenericResponse>> {
        return repository.verifyOtpResetPassword(email)
    }

    override suspend fun changePassword(data: NewPasswordParam): Flow<Resource<KaboorGenericResponse>> {
        return repository.changePassword(data.toRequest())
    }

    override suspend fun checkEmail(email: String): Flow<Resource<KaboorGenericResponse>> {
        return repository.checkEmail(email)
    }
}