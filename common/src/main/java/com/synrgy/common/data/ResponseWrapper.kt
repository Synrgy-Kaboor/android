package com.synrgy.common.data

import com.google.gson.annotations.SerializedName


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


data class ResponseWrapper<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T
)
