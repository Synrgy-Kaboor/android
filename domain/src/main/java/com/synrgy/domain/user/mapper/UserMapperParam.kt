package com.synrgy.domain.user.mapper

import com.synrgy.data.user.model.request.ImageProfileRequest
import com.synrgy.data.user.model.request.PassportRequest
import com.synrgy.data.user.model.request.UpdatePersonalInfoRequest
import com.synrgy.domain.user.model.request.ImageProfileParam
import com.synrgy.domain.user.model.request.PassportParam
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.request.UserParam
import com.synrgy.domain.user.model.response.User

fun UpdatePersonalInfoParam.toRequest(): UpdatePersonalInfoRequest {
    return UpdatePersonalInfoRequest(
        title = title,
        fullName = fullName,
        nik = nik,
        gender = gender,
        birthday = birthday,
        nation = nation,
        city = city,
        address = address,
        isWni = isWni,
        imageName = imageName
    )
}

fun User.toParam(): UserParam {
    return UserParam(
        title = title,
        fullName = fullName,
        email = email,
        gender = gender,
        birthday = birthday,
        nation = nation,
        city = city,
        address = address,
        isWni = isWni,
        imageName = imageName,
        nik = nik,
        phoneNumber = phoneNumber,
        imageUrl = imageUrl,
    )
}

fun ImageProfileParam.toRequest(): ImageProfileRequest {
    return ImageProfileRequest(
        file = file
    )
}

fun PassportParam.toRequest(): PassportRequest {
    return PassportRequest(
        expiredDate = expiredDate,
        fullName = fullName,
        nation = nation,
        passportNumber = passportNumber
    )
}