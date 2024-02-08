package com.synrgy.kaboor.utils

import androidx.lifecycle.MutableLiveData
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.response.Login
import com.wahidabd.library.data.Resource


/**
 * Created by wahid on 1/30/2024.
 * Github github.com/wahidabd.
 */


object DummyDataTest {
    const val validEmail = "email@wa.id"
    const val validPassword = "password"
    const val validName = "wahid"
    const val validPhone = "081234567890"

    const val invalidEmail = "email"
    const val invalidPassword = "pass"

    const val emptyString = ""

    val loginParam = LoginParam(validEmail, validPassword)
    val emailParam = EmailParam(validEmail)

    fun flowGenericSuccess(): Resource<KaboorResponse> {
        return Resource.success(KaboorResponse(200, "success"))
    }

    fun flowGenericError(): Resource<KaboorResponse> {
        return Resource.fail("error")
    }


    fun flowLogin(success: Boolean): Resource<Login> {
        return if (success) Resource.success(Login("token"))
        else Resource.fail("error")
    }

    fun checkEmailResponse(): MutableLiveData<Resource<KaboorResponse>> {
        val response = MutableLiveData<Resource<KaboorResponse>>()
        response.value = flowGenericSuccess()
        return response
    }

    fun loginResponse(): MutableLiveData<Resource<Login>> {
        val response = MutableLiveData<Resource<Login>>()
        response.value = flowLogin(true)
        return response
    }

}