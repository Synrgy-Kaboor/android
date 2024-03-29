package com.synrgy.data.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class PassengerRequest(
    val fullName: String? = null,
    val title: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null
)