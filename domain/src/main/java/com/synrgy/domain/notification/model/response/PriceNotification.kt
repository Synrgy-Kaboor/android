package com.synrgy.domain.notification.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


@Parcelize
data class PriceNotification(
    val id: Int? = 0,
    val totalAdults: Int? = 0,
    val totalChildren: Int? = 0,
    val totalBabies: Int? = 0,
    val classCode: String,
    val minimumPrice: Long? = 0L,
    val maximumPrice: Long? = 0L,
    val date: String,
    val originAirport: AirportCity,
    val destinationAirport: AirportCity,
) : Parcelable {
    fun countPassenger(): Int {
        return (totalAdults ?: 0) + (totalChildren ?: 0) + (totalBabies ?: 0)
    }
}
