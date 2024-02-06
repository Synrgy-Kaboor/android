package com.synrgy.domain.booking.model.request

import android.os.Parcelable
import com.synrgy.data.booking.model.request.PassengerRequest
import com.wahidabd.library.utils.common.emptyString
import kotlinx.parcelize.Parcelize


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */

@Parcelize
data class BookingParam(
    val outboundFlightId: Int,
    val returnFlightId: Int? = null,
    val classCode: String,
    val totalAdult: Int,
    val totalChild: Int,
    val totalBaby: Int,
    val orderer: PassengerParam,
    val passengers: List<PassengerParam>,
    val addBaggage: Boolean = false,
    val addTravelInsurance: Boolean = false,
    val addDelayProtection: Boolean = false,
    val paymentMethod: String? = null,
    val voucherId: Int? = null
): Parcelable
