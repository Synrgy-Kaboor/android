package com.synrgy.domain.user

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.user.model.request.PassportRequest
import com.synrgy.data.user.model.response.PassportDataResponse
import com.synrgy.data.user.model.response.PassportResponse
import com.synrgy.domain.auth.model.request.RegisterParam
import com.synrgy.domain.user.model.request.ImageProfileParam
import com.synrgy.domain.user.model.request.PassportParam
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.ImageProfile
import com.synrgy.domain.user.model.response.Passport
import com.synrgy.domain.user.model.response.PersonalInfo
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
    ): Flow<Resource<PersonalInfo>>

    suspend fun uploadImage(
        body: ImageProfileParam
    ): Flow<Resource<ImageProfile>>

    suspend fun addPassport(
        body: PassportParam,
    ): Flow<Resource<KaboorResponse>>

    suspend fun getAllPassport():
            Flow<Resource<List<Passport>>>

    suspend fun deletePassport(
        id: String,
    ): Flow<Resource<KaboorResponse>>

    suspend fun updatePassport(
        id: String,
        body: PassportParam,
    ): Flow<Resource<Passport>>
}