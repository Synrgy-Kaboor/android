package com.synrgy.domain.booking.model.request

import android.os.Parcelable
import com.synrgy.common.model.AirportData
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/23/2024.
 * Github github.com/wahidabd.
 */

@Parcelize
data class FlightParam(
    val originCity: String,
    val destinationCity: String,
    val departureDate: String,
    val returnDate: String? = null,
    val numOfKids: Int? = 0,
    val numOfBabies: Int? = 0,
    val numOfAdults: Int? = 0,
    val classCode: String,
    val departureData: AirportData? = null,
    val arrivalData: AirportData? = null,
) : Parcelable {
    fun countPassenger(): Int {
        return numOfAdults?.plus(numOfKids ?: 0)?.plus(numOfBabies ?: 0) ?: 0
    }
}
