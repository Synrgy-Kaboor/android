package com.synrgy.data.payment.model.response


/**
 * Created by wahid on 1/22/2024.
 * Github github.com/wahidabd.
 */


data class Promo(
    val type: String,
    val title: String,
    val description: String,
    val voucherCode: String,
    val voucherEnded: Long,
    val saveUpTo: Long
)