package com.synrgy.common.data

import com.google.gson.annotations.SerializedName


/**
 * Created by wahid on 1/25/2024.
 * Github github.com/wahidabd.
 */


data class ResponseListWrapper<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<T>
)
