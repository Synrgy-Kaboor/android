package com.synrgy.data.user.model.request

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


data class ImageProfileRequest(
    val file: File
){
    fun toMultiPart(): MultipartBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
            .build()
    }
}
