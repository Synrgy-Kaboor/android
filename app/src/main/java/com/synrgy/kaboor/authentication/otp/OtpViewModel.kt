package com.synrgy.kaboor.authentication.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
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

    private val _generic = MutableLiveData<Resource<KaboorResponse>>()
    val generic: LiveData<Resource<KaboorResponse>> get() = _generic


    fun verifyOtp(body: OtpParam, type: OtpType) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            when (type) {
                OtpType.REGISTER -> auth.verifiedOTP(body).collectLatest { _generic.value = it }
                OtpType.FORGOT_PASSWORD -> auth.verifyOtpResetPassword(body)
                    .collectLatest { _generic.value = it }

                OtpType.CHANGE_EMAIL -> auth.verifyOtpChangeEmail(body)
                    .collectLatest { _generic.value = it }

                OtpType.CHANGE_PHONE -> auth.verifyOtpChangePhone(body)
                    .collectLatest { _generic.value = it }
            }
        }
    }

    fun resendOtp(body: EmailParam? = null, type: OtpType) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            when (type) {
                OtpType.REGISTER -> auth.resendOTP(body!!).collectLatest { _generic.value = it }
                OtpType.FORGOT_PASSWORD -> auth.resendOtpPassword(body!!)
                    .collectLatest { _generic.value = it }
                OtpType.CHANGE_EMAIL -> auth.resendOtpEmail().collectLatest { _generic.value = it }
                OtpType.CHANGE_PHONE -> auth.resendOtpNumber().collectLatest { _generic.value = it }
            }
        }
    }
}