package com.synrgy.domain.user.mapper

import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.data.user.model.request.UserRequest
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.request.UserParam


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

fun UpdatePersonalInfoParam.toRequest(): UpdatePersonalInfoRequest {
    return UpdatePersonalInfoRequest(
        title = title,
        fullName = fullName,
        gender = gender,
        birthday = birthday,
        country = country,
        city = city,
        address = address,
        email = email,
        phoneNumber = phoneNumber,
        isWni = isWni
    )
}