package com.synrgy.kaboor.authentication.change

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.auth.AuthUseCase
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.PhoneParam
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.mapper.toParam
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ChangeViewModel(
    private val auth: AuthUseCase
) : ViewModel() {

    private val _generic = MutableLiveData<Resource<KaboorResponse>>()
    val generic: LiveData<Resource<KaboorResponse>> get() = _generic



    fun changeEmail(body: EmailParam) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.changeEmail(body)
                .collectLatest { _generic.value = it }
        }
    }

    fun changePhone(body: PhoneParam){
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.changePhone(body)
                .collectLatest { _generic.value = it }
        }
    }
}