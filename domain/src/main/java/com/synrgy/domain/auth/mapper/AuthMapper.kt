package com.synrgy.domain.auth.mapper

import com.synrgy.data.auth.model.request.EmailRequest
import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.NewPasswordRequest
import com.synrgy.data.auth.model.request.OtpRequest
import com.synrgy.data.auth.model.request.PhoneRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.user.model.response.UserDataResponse
import com.synrgy.data.user.model.response.UserResponse
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.request.NewPasswordParam
import com.synrgy.domain.auth.model.request.OtpParam
import com.synrgy.domain.auth.model.request.PhoneParam
import com.synrgy.domain.auth.model.request.RegisterParam
import com.synrgy.domain.auth.model.response.Login
import com.synrgy.domain.user.model.response.User


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


fun LoginResponse.toDomain(): Login {
    return Login(jwt = auth.jwt)
}

fun UserResponse.toDomain(): User {
    return User(
        phoneNumber = user.phoneNumber,
        fullName = user.fullName,
        email = user.email,
    )
}

fun UserDataResponse.toDomain(): User {
    return User(
        phoneNumber = phoneNumber,
        fullName = fullName,
        email = email,
        title = title,
        gender = gender,
        birthday = birthday,
        nik = nik,
        nation = nation,
        city = city,
        address = address,
        isWni = isWni,
        imageName = imageName,
        imageUrl = imageUrl
    )
}

fun RegisterParam.toRequest(): RegisterRequest {
    return RegisterRequest(
        phoneNumber = phoneNumber,
        fullName = fullName,
        email = email,
        password = password
    )
}

fun LoginParam.toRequest(): LoginRequest {
    return LoginRequest(
        email = email,
        password = password
    )
}

fun NewPasswordParam.toRequest(): NewPasswordRequest{
    return NewPasswordRequest(
        email = email,
        newPassword = newPassword
    )
}

fun EmailParam.toRequest(): EmailRequest {
    return EmailRequest(
        email = email
    )
}

fun OtpParam.toRequest(): OtpRequest {
    return OtpRequest(
        otp = otp
    )
}

fun PhoneParam.toRequest(): PhoneRequest {
    return PhoneRequest(
        phoneNumber = number
    )
}


