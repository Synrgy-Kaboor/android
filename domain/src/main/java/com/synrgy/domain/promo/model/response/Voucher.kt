package com.synrgy.domain.promo.model.response


/**
 * Created by wahid on 1/22/2024.
 * Github github.com/wahidabd.
 */


data class Voucher(
    val id: Int,
    val code: String,
    val title: String,
    val description: String,
    val eligiblePaymentMethods: List<String>,
    val maximumDiscount: Long,
    val expiredTime: String,
    var isEligible: Boolean = false
)