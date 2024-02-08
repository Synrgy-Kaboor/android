package com.synrgy.domain.promo.model.response

import android.os.Parcelable
import androidx.annotation.ArrayRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    val id: Int,
    val methodName: String,
    val code: String,
    val imageUrl: String,
    @ArrayRes val atm: Int,
    @ArrayRes val internet: Int,
    @ArrayRes val mobile: Int
): Parcelable
