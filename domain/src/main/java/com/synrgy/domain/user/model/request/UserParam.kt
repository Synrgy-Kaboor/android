package com.synrgy.domain.user.model.request


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


data class UserParam(
    val title: String? = null,
    val fullName: String? = null,
    val gender: String? = null,
    val birthday: String? = null,
    val country: String? = null,
    val nation: String? = null,
    val city: String? = null,
    val address: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val isWni: Boolean? = false,
    val verified: Boolean = false,
)