package com.synrgy.domain.user.mapper

import com.synrgy.data.booking.model.response.BookingInfoResponse
import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.PersonalInfoResponse
import com.synrgy.domain.booking.mapper.toDomain
import com.synrgy.domain.booking.model.response.BookingInfo
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.PersonalInfo


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


fun UserParam.toRequest(): UserRequest {
    return UserRequest(
        fullName = fullName,
        email = email,
        phoneNumber = phoneNumber
    )
}

fun PersonalInfoResponse.toDomain(): PersonalInfo {
    return PersonalInfo(
        title = title,
        fullName = fullName,
        gender = gender,
        birthday = birthday,
        nation = nation,
        city = city,
        address = address,
        isWni = isWni,
    )
}