package com.synrgy.data.booking.model.request

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by wahid on 2/7/2024.
 * Github github.com/wahidabd.
 */


data class ProofRequest(
    val file: File
){
    fun toMultiPart(): MultipartBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
            .build()
    }
}
