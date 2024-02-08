package com.synrgy.domain.user.mapper

import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.data.user.model.response.PersonalInfoResponse
import com.synrgy.domain.booking.model.response.Passenger
import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.PersonalInfo
import com.synrgy.domain.user.model.response.User


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

fun PersonalInfoResponse.toUser(): User {
    return User(
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

fun User.toPassenger(): Passenger {
    return Passenger(
        fullName = fullName,
        email = email,
        phoneNumber = phoneNumber,
        title = title
    )
}