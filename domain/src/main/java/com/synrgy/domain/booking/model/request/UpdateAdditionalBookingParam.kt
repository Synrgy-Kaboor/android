package com.synrgy.domain.booking.model.request


/**
 * Created by wahid on 1/29/2024.
 * Github github.com/wahidabd.
 */


data class UpdateAdditionalBookingParam(
    val addTravelInsurance: Boolean? = false,
    val addBaggageInsurance: Boolean? = false,
    val addDelayProtection: Boolean? = false,
)
