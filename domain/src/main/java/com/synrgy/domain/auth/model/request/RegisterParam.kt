package com.synrgy.domain.auth.model.request


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


data class RegisterParam(
    val phoneNumber: String,
    val email: String,
    val fullName: String,
    val password: String,
)
