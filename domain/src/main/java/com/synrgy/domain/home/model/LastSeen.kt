package com.synrgy.domain.home.model

import androidx.annotation.DrawableRes


/**
 * Created by wahid on 12/31/2023.
 * Github github.com/wahidabd.
 */


// TODO: Change this if API is ready and use this model
data class LastSeen(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int
)
