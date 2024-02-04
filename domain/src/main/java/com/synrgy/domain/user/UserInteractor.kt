package com.synrgy.domain.user

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.user.UserRepository
import com.synrgy.data.user.model.response.PersonalInfoResponse
import com.synrgy.domain.auth.mapper.toDomain
import com.synrgy.domain.user.mapper.toDomain
import com.synrgy.domain.user.mapper.toRequest
import com.synrgy.domain.user.mapper.toUser
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.PersonalInfo
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


class UserInteractor(private val repository: UserRepository) : UserUseCase {

    override suspend fun saveToken(token: String) {
        return repository.saveToken(token)
    }

    override suspend fun clearToken() {
        return repository.clearToken()
    }

    override suspend fun setLogin(isLogin: Boolean) {
        return repository.setLogin(isLogin)
    }

    override fun getLogin(): Flow<Boolean> {
        return repository.getLogin()
    }

    override suspend fun setUser(data: UserParam) {
        return repository.setUser(data.toRequest())
    }

    override fun getUser(): Flow<User> {
        return repository.getUser().map { user ->
            user.toDomain()
        }
    }

    override suspend fun getPersonalInfo(): Flow<Resource<User>> {
        return object :
            InternetBoundResource<User, ResponseWrapper<PersonalInfoResponse>>() {
            override suspend fun createCall(): Flow<Resource<ResponseWrapper<PersonalInfoResponse>>> {
                return repository.getPersonalInfo()
            }

            override suspend fun saveCallRequest(data: ResponseWrapper<PersonalInfoResponse>): User {
                return data.data.toUser()
            }
        }.asFlow()
    }

    override suspend fun updatePersonalInfo(body: UpdatePersonalInfoParam): Flow<Resource<KaboorGenericResponse>> {
        return repository.updatePersonalInfo(body.toRequest())
    }

}