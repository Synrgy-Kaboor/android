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
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.mapper.toParam
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


class OtpViewModel(
    private val auth: AuthUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {


    private val _generic = MutableLiveData<Resource<KaboorResponse>>()
    val generic: LiveData<Resource<KaboorResponse>> get() = _generic

    private val _resend = MutableLiveData<Resource<KaboorResponse>>()
    val resend: LiveData<Resource<KaboorResponse>> get() = _resend

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData


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
        _resend.value = Resource.loading()
        viewModelScope.launch {
            when (type) {
                OtpType.REGISTER -> auth.resendOTP(body!!).collectLatest { _resend.value = it }
                OtpType.FORGOT_PASSWORD -> auth.resendOtpPassword(body!!)
                    .collectLatest { _resend.value = it }
                OtpType.CHANGE_EMAIL -> auth.resendOtpEmail().collectLatest { _resend.value = it }
                OtpType.CHANGE_PHONE -> auth.resendOtpNumber().collectLatest { _resend.value = it }
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            userUseCase.getUser()
                .collectLatest { _userData.value = it }
        }
    }

    fun saveUserInfo(data: User){
        viewModelScope.launch {
            userUseCase.setUser(data.toParam())
        }
    }
}