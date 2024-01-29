package com.synrgy.data.booking.model.response


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class PaymentDetailResponse(
    val methodName: String? = null,
    val accountNumber: String? = null,
    val totalPrice: Long? = 0L,
    val paymentCompleted: Boolean? = false,
    val expiredTime: String? = null,
)
