package com.synrgy.domain.user.mapper

import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam

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