package com.synrgy.domain.user.model.request

data class PassportParam(
    val expiredDate: String,
    val fullName: String,
    val nation: String,
    val passportNumber: String
)