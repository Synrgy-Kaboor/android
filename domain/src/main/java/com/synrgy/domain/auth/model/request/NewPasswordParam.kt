package com.synrgy.domain.auth.model.request


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


data class NewPasswordParam(
    val email: String,
    val newPassword: String
)
