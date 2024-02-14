package com.synrgy.data.user.model.request

data class PassportRequest(
    val expiredDate: String,
    val fullName: String,
    val nation: String,
    val passportNumber: String
)