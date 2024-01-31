package com.synrgy.domain.user.model.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


data class User(
    val title: String? = emptyString(),
    val fullName: String? = emptyString(),
    val gender: String? = emptyString(),
    val birthday: String? = emptyString(),
    val country: String? = emptyString(),
    val nation: String? = emptyString(),
    val city: String? = emptyString(),
    val address: String? = emptyString(),
    val email: String? = emptyString(),
    val phoneNumber: String? = emptyString(),
    val isWni: Boolean? = false,
    val verified: Boolean = false,
)
