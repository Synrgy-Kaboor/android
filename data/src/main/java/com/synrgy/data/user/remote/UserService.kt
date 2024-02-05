package com.synrgy.data.user.remote

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.data.user.model.response.PersonalInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


interface UserService {
    @GET("/api/v1/user")
    suspend fun getPersonalInfo(): Response<ResponseWrapper<PersonalInfoResponse>>

    @PATCH("/api/v1/user")
    suspend fun updatePersonalInfo(
        @Body body: UpdatePersonalInfoRequest,
    ): Response<KaboorGenericResponse>
}