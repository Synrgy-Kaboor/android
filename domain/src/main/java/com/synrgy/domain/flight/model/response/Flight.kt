package com.synrgy.domain.flight.model.response

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.wahidabd.library.utils.common.emptyString
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


@Parcelize
data class Flight(
    val id: Int = 0,
    val departureDatetime: String = emptyString(),
    val arrivalDatetime: String = emptyString(),
    val plane: Plane,
    val originAirport: Airport,
    val destinationAirport: Airport,
    val adultPrice: Long = 0L,
    val childPrice: Long = 0L,
    val babyPrice: Long = 0L
) : Parcelable

