package com.synrgy.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/5/2024.
 * Github github.com/wahidabd.
 */

@Parcelize
data class AirportData(
    val city: String,
    val airport: String,
): Parcelable
