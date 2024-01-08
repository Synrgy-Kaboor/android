package com.synrgy.data.auth.model.response


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


data class UserResponse(
    val phoneNumber: String,
    val email: String,
    val fullName: String,
    val verified: Boolean = false,
)
