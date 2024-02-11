package com.synrgy.kaboor.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.model.request.ImageProfileParam
import com.synrgy.domain.user.model.request.UpdatePersonalInfoParam
import com.synrgy.domain.user.model.response.ImageProfile
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

    private val _imageProfile = MutableLiveData<Resource<ImageProfile>>()
    val imageProfile: LiveData<Resource<ImageProfile>> get() = _imageProfile

    private val _profile = MutableLiveData<String>()
    val profile: LiveData<String> get() = _profile

    private val _logout = MutableLiveData<Resource<Unit>>()
    val logout: LiveData<Resource<Unit>> get() = _logout

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    private val _percentage = MutableLiveData<Int>()
    val percentage: LiveData<Int> get() = _percentage

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

    fun uploadImage(body: ImageProfileParam) {
        _imageProfile.value = Resource.loading()
        viewModelScope.launch {
            user.uploadImage(body)
                .collectLatest {
                    _imageProfile.value = it
                }
        }
    }

    fun setProfile(data: String) {
        viewModelScope.launch {
            user.setProfile(data)
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            user.getProfile().collectLatest {
                _profile.value = it
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            user.getUser()
                .collectLatest { _userData.value = it }
        }
    }

    fun clearToken() {
        viewModelScope.launch {
            user.clearToken()
            user.setLogin(false)
            _logout.value = Resource.success(Unit)
        }
    }

    fun getPercentage() {
        _percentage.value = user.getPercentage()
    }
}