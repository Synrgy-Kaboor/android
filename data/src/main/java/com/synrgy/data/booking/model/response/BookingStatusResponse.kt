package com.synrgy.data.booking.model.response


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingStatusResponse(
    val methodName: String? = null,
    val totalPrice: Long? = 0L,
    val expiredTime: String? = null,
    val paymentCompleted: Boolean? = false,
    val paymentDateTime: String? = null,
    val invoiceNumber: String? = null
)
