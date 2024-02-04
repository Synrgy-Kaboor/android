package com.synrgy.data.user

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.db.KaboorDataStore
import com.synrgy.common.utils.ext.flowDispatcherIO
import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.PersonalInfoResponse
import com.synrgy.data.user.model.response.UserDataResponse
import com.synrgy.data.user.remote.UserService
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.enqueue
import com.wahidabd.library.utils.coroutine.handler.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


class UserDataStore(
    private val dataStore: KaboorDataStore,
    private val api: UserService,
    private val error: ErrorParser,
) : UserRepository {

    override suspend fun saveToken(token: String) {
        dataStore.saveToken(token)
    }

    override suspend fun clearToken() {
        dataStore.clearToken()
    }

    override suspend fun setLogin(isLogin: Boolean) {
        dataStore.getLogin(isLogin)
    }

    override fun getLogin(): Flow<Boolean> {
        return dataStore.getLogin()
    }

    override suspend fun setUser(data: UserRequest) {
        dataStore.setUser(data)
    }

    override fun getUser(): Flow<UserDataResponse> {
        return dataStore.getUser()
    }

    override suspend fun getPersonalInfo(): Flow<Resource<ResponseWrapper<PersonalInfoResponse>>> = flow {
        enqueue(
            error::convertGenericError,
            api::getPersonalInfo,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()

    override suspend fun updatePersonalInfo(body: UpdatePersonalInfoRequest): Flow<Resource<KaboorGenericResponse>> = flow {
        enqueue(
            body,
            error::convertGenericError,
            api::updatePersonalInfo,
            onEmit = { data -> emit(data) }
        )
    }.flowDispatcherIO()
}