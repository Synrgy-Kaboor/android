package com.synrgy.kaboor.authentication.forgot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.domain.auth.AuthUseCase
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by wahid on 1/13/2024.
 * Github github.com/wahidabd.
 */


class ForgotPasswordViewModel(
    private val auth: AuthUseCase
) : ViewModel() {

    private val _generic = MutableLiveData<Resource<KaboorGenericResponse>>()
    val generic: LiveData<Resource<KaboorGenericResponse>> get() = _generic


    fun forgotPassword(email: String) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.forgetPassword(email)
                .collectLatest { _generic.value = it }
        }
    }

}