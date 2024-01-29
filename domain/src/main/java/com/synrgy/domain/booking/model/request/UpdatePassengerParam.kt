package com.synrgy.domain.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class UpdatePassengerParam(
    val fullName: String? = null,
    val title: String? = null,
    val phone_number: String? = null,
    val email: String? = null
)