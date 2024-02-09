package com.synrgy.domain.user

import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.user.model.request.ImageProfileParam
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


interface UserUseCase {
    suspend fun saveToken(token: String)
    suspend fun clearToken()
    suspend fun setLogin(isLogin: Boolean)
    fun getLogin(): Flow<Boolean>
    suspend fun setUser(data: UserParam)
    fun getUser(): Flow<User>
    suspend fun setProfile(data: String)
    fun getProfile(): Flow<String>
    fun getPercentage(): Int

    suspend fun getPersonalInfo(): Flow<Resource<User>>

    suspend fun updatePersonalInfo(
        body: UpdatePersonalInfoParam,
    ): Flow<Resource<KaboorResponse>>

    suspend fun uploadImage(
        body: ImageProfileParam,
    ): Flow<Resource<KaboorResponse>>

//    suspend fun uploadImage(
//        body: ImageProfileParam
//    ): Flow<Resource<ImageProfile>>
}