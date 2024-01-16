package com.synrgy.domain.user

import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.User
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


interface UserUseCase {
    suspend fun saveToken(token: String)
    suspend fun setUser(data: UserParam)
    fun getUser(): Flow<User>
}