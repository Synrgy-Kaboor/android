package com.synrgy.data.user.model.response

import com.google.gson.annotations.SerializedName

data class PassportResponse(
    val passport: List<PassportDataResponse>
)

data class PassportDataResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("deleted_at")
    val deletedAt: String? = null,
    @SerializedName("expired_date")
    val expiredDate: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("nation")
    val nation: String,
    @SerializedName("passport_number")
    val passportNumber: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)