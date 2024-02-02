package com.synrgy.domain.flight.model.response

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderFlight(
    val id: String,
    val departure: String,
    val destination: String,
    val boardingDate: String,
    val boardingTime: String,
    val orderStatus: String,
    @DrawableRes val image: Int? = null
) : Parcelable