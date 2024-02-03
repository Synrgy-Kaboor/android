package com.synrgy.domain.user

import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.domain.booking.model.request.UpdateBookingParam
import com.synrgy.domain.booking.model.response.BookingInfo
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.request.UserParam
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

    suspend fun getPersonalInfo(
        id: Int
    ): Flow<Resource<PersonalInfo>>

    suspend fun updatePersonalInfo(
        id: Int,
        body: UpdatePersonalInfoParam
    ): Flow<Resource<KaboorGenericResponse>>
}