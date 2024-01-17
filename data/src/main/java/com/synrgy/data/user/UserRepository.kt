package com.synrgy.data.user

import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.UserDataResponse
import com.synrgy.data.user.model.response.UserResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


interface UserRepository {

    suspend fun saveToken(token: String)
    suspend fun setUser(data: UserRequest)
    fun getUser(): Flow<UserDataResponse>

}