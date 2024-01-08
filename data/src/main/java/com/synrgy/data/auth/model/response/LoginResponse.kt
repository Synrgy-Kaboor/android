package com.synrgy.data.auth.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


data class LoginResponse(
    @SerializedName("jwt")
    val jwt: String
)
