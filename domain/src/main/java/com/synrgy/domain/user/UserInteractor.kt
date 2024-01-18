package com.synrgy.domain.user

import com.synrgy.data.user.UserRepository
import com.synrgy.domain.auth.mapper.toDomain
import com.synrgy.domain.user.mapper.toRequest
import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.User
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

}