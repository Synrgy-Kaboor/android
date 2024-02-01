package com.synrgy.domain.user.model.response


/**
 * Created by wahid on 2/1/2024.
 * Github github.com/wahidabd.
 */


data class Passport(
    val id: Int,
    val fullName: String,
    val passportNumber: String,
    val expiredDate: String,
    val nation: String
)
