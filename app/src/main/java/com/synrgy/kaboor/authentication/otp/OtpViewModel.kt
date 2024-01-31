package com.synrgy.kaboor.authentication.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.common.utils.enums.OtpType
import com.synrgy.domain.auth.AuthUseCase
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.OtpParam
import com.synrgy.domain.auth.model.request.PhoneParam
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


class OtpViewModel(
    private val auth: AuthUseCase
) : ViewModel() {

    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>> get() = _user

    private val _generic = MutableLiveData<Resource<KaboorGenericResponse>>()
    val generic: LiveData<Resource<KaboorGenericResponse>> get() = _generic


    fun verifyOtp(body: OtpParam, type: OtpType?) {
        viewModelScope.launch {
            when (type) {
                OtpType.REGISTER -> auth.verifiedOTP(body).collectLatest { _user.value = it }
                OtpType.FORGOT_PASSWORD -> auth.verifyOtpResetPassword(body)
                    .collectLatest { _generic.value = it }
                else -> _generic.value = Resource.fail("Unknown Error")
            }
        }
    }

    fun resendOtp(body: EmailParam) {
        viewModelScope.launch {
            auth.resendOTP(body)
                .collectLatest { _generic.value = it }
        }
    }

    fun changeEmail(body: EmailParam) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.changeEmail(body)
                .collectLatest { _generic.value = it }
        }
    }

    fun changeNumber(body: PhoneParam) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.changePhone(body)
                .collectLatest { _generic.value = it }
        }
    }
}