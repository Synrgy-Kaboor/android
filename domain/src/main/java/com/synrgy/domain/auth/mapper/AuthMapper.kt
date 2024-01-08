package com.synrgy.domain.auth.mapper

import com.synrgy.data.auth.model.request.LoginRequest
import com.synrgy.data.auth.model.request.RegisterRequest
import com.synrgy.data.auth.model.response.LoginResponse
import com.synrgy.data.auth.model.response.UserResponse
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.request.RegisterParam
import com.synrgy.domain.auth.model.response.Login
import com.synrgy.domain.auth.model.response.User


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


fun LoginResponse.toDomain(): Login {
    return Login(jwt = jwt)
}

fun UserResponse.toDomain(): User {
    return User(
        phoneNumber = phoneNumber,
        fullName = fullName,
        email = email,
        verified = verified
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