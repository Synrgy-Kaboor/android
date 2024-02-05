package com.synrgy.data.user.model.request

data class UpdatePersonalInfoRequest(
    val title: String,
    val fullName: String,
    val gender: String,
    val birthday: String,
    val nation: String,
    val city: String,
    val address: String,
    val isWni: Boolean
)
