package com.synrgy.data.user.remote

import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.data.user.model.response.ImageProfileResponse
import com.synrgy.data.user.model.response.PersonalInfoResponse
import com.synrgy.data.user.model.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


interface UserService {

    @POST("/api/v1/auth/register/user")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<ResponseWrapper<UserResponse>>

    @GET("/api/v1/user")
    suspend fun getPersonalInfo(): Response<ResponseWrapper<PersonalInfoResponse>>

    @PATCH("/api/v1/user")
    suspend fun updatePersonalInfo(
        @Body body: UpdatePersonalInfoRequest,
    ): Response<ResponseWrapper<PersonalInfoResponse>>

    @POST("api/v1/user/image")
    suspend fun uploadImage(
        @Body body: MultipartBody,
    ): Response<ImageProfileResponse>
}