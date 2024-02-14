package com.synrgy.data.user.remote

import com.synrgy.common.data.ListWrapper
import com.synrgy.common.data.ResponseWrapper
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.user.model.request.PassportRequest
import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.data.user.model.response.ImageProfileResponse
import com.synrgy.data.user.model.response.PassportDataResponse
import com.synrgy.data.user.model.response.PassportResponse
import com.synrgy.data.user.model.response.PersonalInfoResponse
import com.synrgy.data.user.model.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


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
    ): Response<ResponseWrapper<PersonalInfoResponse>>

    @POST("api/v1/user/image")
    suspend fun uploadImage(
        @Body body: MultipartBody,
    ): Response<ResponseWrapper<ImageProfileResponse>>

    @POST("/api/v1/user/passport")
    suspend fun addPassport(
        @Body body: PassportRequest,
    ): Response<KaboorResponse>

    @GET("/api/v1/user/passport")
    suspend fun getAllPassport(): Response<ResponseWrapper<PassportResponse>>

    @DELETE("/api/v1/user/passport/{id}")
    suspend fun deletePassport(
        @Path("id") id: String,
    ): Response<KaboorResponse>

    @PATCH("/api/v1/user/passport/{id}")
    suspend fun updatePassport(
        @Path("id") id: String,
        @Body body: PassportRequest,
    ): Response<ResponseWrapper<PassportDataResponse>>

}