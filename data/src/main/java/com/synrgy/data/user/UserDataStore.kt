package com.synrgy.data.user

import com.synrgy.data.user.local.KaboorDataStore
import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.UserDataResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


class UserDataStore (private val dataStore: KaboorDataStore) : UserRepository {

    override suspend fun saveToken(token: String) {
        dataStore.saveToken(token)
    }

    override suspend fun clearToken() {
        dataStore.clearToken()
    }

    override suspend fun setLogin(isLogin: Boolean) {
        dataStore.setLogin(isLogin)
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
}