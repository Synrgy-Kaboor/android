package com.synrgy.data.user.model.response


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


data class UserResponse(
    val user: UserDataResponse
)

data class UserDataResponse(
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val verified: Boolean = false,
)