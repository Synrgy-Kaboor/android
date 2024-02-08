package com.synrgy.domain.booking.model.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class Passenger(
    val fullName: String? = emptyString(),
    val title: String? = emptyString(),
    val phoneNumber: String? = emptyString(),
    val email: String? = emptyString()
)
