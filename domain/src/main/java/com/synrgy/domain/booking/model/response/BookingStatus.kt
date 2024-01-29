package com.synrgy.domain.booking.model.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class BookingStatus(
    val methodName: String? = emptyString(),
    val totalPrice: Long? = 0L,
    val expiredTime: String? = emptyString(),
    val paymentCompleted: Boolean? = false,
    val paymentDateTime: String? = emptyString(),
    val invoiceNumber: String? = emptyString()
)
