package com.synrgy.data.user.model.request


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


data class UserRequest(
    val title: String? = null,
    val fullName: String? = null,
    val gender: String? = null,
    val birthday: String? = null,
    val nation: String? = null,
    val city: String? = null,
    val address: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val isWni: Boolean? = false,
    val imageName: String? = null,
    val imageUrl: String? = null,
    val nik: String? = null
)