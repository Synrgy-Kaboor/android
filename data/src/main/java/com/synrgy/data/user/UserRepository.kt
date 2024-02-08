package com.synrgy.data.user

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.PersonalInfoResponse
import com.synrgy.data.user.model.response.UserDataResponse
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


interface UserRepository {

    suspend fun saveToken(token: String)
    suspend fun clearToken()
    suspend fun setLogin(isLogin: Boolean)
    fun getLogin(): Flow<Boolean>
    suspend fun setUser(data: UserRequest)
    fun getUser(): Flow<UserDataResponse>
    suspend fun getPersonalInfo(): Flow<Resource<ResponseWrapper<PersonalInfoResponse>>>

    suspend fun updatePersonalInfo(
        body: UpdatePersonalInfoRequest,
    ): Flow<Resource<KaboorResponse>>

}