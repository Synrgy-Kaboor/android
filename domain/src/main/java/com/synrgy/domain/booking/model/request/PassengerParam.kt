package com.synrgy.domain.booking.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */

@Parcelize
data class PassengerParam(
    val fullName: String? = null,
    val title: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null
): Parcelable