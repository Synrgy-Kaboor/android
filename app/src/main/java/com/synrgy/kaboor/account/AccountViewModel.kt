package com.synrgy.kaboor.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AccountViewModel(
    private val user: UserUseCase,
) : ViewModel() {

    private val _getPersonalInfo = MutableLiveData<Resource<User>>()
    val getPersonalInfo: LiveData<Resource<User>> get() = _getPersonalInfo

    private val _updatePersonalInfo = MutableLiveData<Resource<KaboorResponse>>()
    val updatePersonalInfo: LiveData<Resource<KaboorResponse>> get() = _updatePersonalInfo

    fun getPersonalInfo() {
        _getPersonalInfo.value = Resource.loading()
        viewModelScope.launch {
            user.getPersonalInfo()
                .collectLatest {
                    _getPersonalInfo.value = it
                }
        }
    }

    fun updatePersonalInfo(body: UpdatePersonalInfoParam) {
        _updatePersonalInfo.value = Resource.loading()
        viewModelScope.launch {
            user.updatePersonalInfo(body)
                .collectLatest {
                    _updatePersonalInfo.value = it
                }
        }
    }
}