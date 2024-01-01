package com.synrgy.domain.home.model

import androidx.annotation.DrawableRes


/**
 * Created by wahid on 1/1/2024.
 * Github github.com/wahidabd.
 */


data class Product(
    val id: Int,
    val title: String,
    val type: String,
    val rating: Float,
    val price: Long,
    @DrawableRes val image: Int? = null
)