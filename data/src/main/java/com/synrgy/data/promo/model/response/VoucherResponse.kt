package com.synrgy.data.promo.model.response


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


data class VoucherResponse(
    val id: Int,
    val code: String,
    val title: String,
    val description: String,
    val eligiblePaymentMethods: List<String>,
    val maximumDiscount: Long,
    val expiredTime: String
)
