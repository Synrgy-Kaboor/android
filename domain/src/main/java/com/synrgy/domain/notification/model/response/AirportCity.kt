package com.synrgy.domain.notification.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


@Parcelize
data class AirportCity(
    val id: Int,
    val code: String,
    val name: String,
    val timezone: Int
): Parcelable
