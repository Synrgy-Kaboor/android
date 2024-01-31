package com.synrgy.data.user.model.request

data class UpdatePersonalInfoRequest(
    val title: String,
    val fullName: String,
    val gender: String,
    val birthday: String,
    val country: String,
    val city: String,
    val address: String,
    val email: String,
    val phoneNumber: String,
    val isWni: Boolean
)
