package com.synrgy.data.flight.model.response

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 2/4/2024.
 * Github github.com/wahidabd.
 */


data class PlaneResponse(
    val id: Int? = 0,
    val name: String? = emptyString(),
    val code: String? = emptyString(),
    val airline: AirlineResponse?
)
