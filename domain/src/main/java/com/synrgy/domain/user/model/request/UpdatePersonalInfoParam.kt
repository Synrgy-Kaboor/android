package com.synrgy.domain.user.model.request

data class UpdatePersonalInfoParam(
    val title: String,
    val fullName: String,
    val gender: String,
    val birthday: String,
    val nik: String,
    val nation: String,
    val city: String,
    val address: String,
    val isWni: Boolean,
    val imageName: String
)
