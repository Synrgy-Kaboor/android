package com.synrgy.domain.auth.model.request


/**
 * Created by wahid on 1/17/2024.
 * Github github.com/wahidabd.
 */


data class OtpParam(
    val email: String? = null,
    val otp: String
)