package com.synrgy.domain.payment

import androidx.annotation.DrawableRes

data class Bank(
    val id: Int,
    val bankName: String,
    @DrawableRes val image: Int? = null
)
