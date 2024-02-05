package com.synrgy.domain.flight.mapper

import com.synrgy.data.flight.model.request.FlightRequest
import com.synrgy.domain.flight.model.request.FlightParam


/**
 * Created by wahid on 2/4/2024.
 * Github github.com/wahidabd.
 */


fun FlightParam.toRequest(): FlightRequest {
    return FlightRequest(
        originCity = this.originCity,
        destinationCity = this.destinationCity,
        departureDate = this.departureDate,
        returnDate = this.returnDate.orEmpty(),
        numOfKids = this.numOfKids,
        numOfBabies = this.numOfBabies,
        numOfAdults = this.numOfAdults,
        classCode = this.classCode,
        isReturn = isReturn
    )
}