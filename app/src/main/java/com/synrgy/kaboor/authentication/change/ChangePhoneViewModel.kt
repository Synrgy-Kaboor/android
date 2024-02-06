package com.synrgy.kaboor.authentication.change

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.domain.auth.AuthUseCase
import com.synrgy.domain.auth.model.request.PhoneParam
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChangePhoneViewModel(
    private val auth: AuthUseCase
) : ViewModel() {

    private val _generic = MutableLiveData<Resource<KaboorGenericResponse>>()
    val generic: LiveData<Resource<KaboorGenericResponse>> get() = _generic


    fun changeNumber(body: PhoneParam) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.changePhone(body)
                .collectLatest { _generic.value = it }
        }
    }

}