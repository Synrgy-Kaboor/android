package com.synrgy.kaboor.authentication.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.domain.auth.AuthUseCase
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.OtpParam
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


    fun verifyOtp(body: OtpParam) {
        viewModelScope.launch {
            auth.verifiedOTP(body)
                .collectLatest { _user.value = it }
        }
    }

    fun resendOtp(body: EmailParam) {
        viewModelScope.launch {
            auth.resendOTP(body)
                .collectLatest { _user.value = it }
        }
    }
}