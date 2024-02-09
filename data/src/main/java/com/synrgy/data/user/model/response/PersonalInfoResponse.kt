package com.synrgy.data.user.model.response

data class PersonalInfoResponse (
    val title: String? = null,
    val fullName: String? = null,
    val gender: String? = null,
    val birthday: String? = null,
    val nik: String? = null,
    val nation: String? = null,
    val city: String? = null,
    val address: String? = null,
    val isWni: Boolean? = false,
    val imageName: String? = null,
    val imageUrl: String? = null
)