package com.synrgy.domain.notification.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


@Parcelize
data class PriceNotification(
    val originCity: AirportCity,
    val destinationCity: AirportCity,
    val departureDate: String,
    val returnDate: String? = null,
    val numOfAdults: Int? = 0,
    val numOfKids: Int? = 0,
    val numOfBabies: Int? = 0,
    @SerializedName("class")
    val clazz: String,
    val lowerPriceLimit: Long? = 0L,
    val upperPriceLimit: Long? = 0L,
) : Parcelable {
    fun countPassenger(): Int {
        return (numOfAdults ?: 0) + (numOfKids ?: 0) + (numOfBabies ?: 0)
    }
}
