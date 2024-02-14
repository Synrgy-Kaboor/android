package com.synrgy.domain.user.model.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 2/1/2024.
 * Github github.com/wahidabd.
 */


data class Passport(
    val createdAt: String,
    val deletedAt: String? = emptyString(),
    val expiredDate: String,
    val fullName: String,
    val id: String,
    val nation: String,
    val passportNumber: String,
    val updatedAt: String,
    val userId: String
)
