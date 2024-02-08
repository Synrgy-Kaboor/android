package com.synrgy.domain.booking.model.request

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


/**
 * Created by wahid on 2/7/2024.
 * Github github.com/wahidabd.
 */


data class ProofParam(
    val file: File
)
