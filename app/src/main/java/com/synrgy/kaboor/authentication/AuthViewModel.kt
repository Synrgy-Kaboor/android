package com.synrgy.kaboor.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.auth.AuthUseCase
import com.synrgy.domain.auth.model.request.EmailParam
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.request.RegisterParam
import com.synrgy.domain.auth.model.response.Login
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.mapper.toParam
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


class AuthViewModel(
    private val auth: AuthUseCase,
    private val user: UserUseCase,
) : ViewModel() {

    private val _userData = MutableLiveData<Resource<User>>()
    val userData: LiveData<Resource<User>> get() = _userData

    private val _jwt = MutableLiveData<Resource<Login>>()
    val jwt: LiveData<Resource<Login>> get() = _jwt

    private val _generic = MutableLiveData<Resource<KaboorResponse>>()
    val generic: LiveData<Resource<KaboorResponse>> get() = _generic



    fun saveToken(token: String) {
        _userData.value = Resource.loading()
        viewModelScope.launch {
            user.setLogin(true)
            user.saveToken(token)
        }
    }

    fun saveUserInfo(data: User){
        viewModelScope.launch {
            user.setUser(data.toParam())
        }
    }

    fun checkEmail(body: EmailParam) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.checkEmail(body)
                .collectLatest { _generic.value = it }
        }
    }

    fun login(body: LoginParam) {
        viewModelScope.launch {
            auth.login(body)
                .collectLatest {
                    _jwt.value = it
                }
        }
    }

    fun register(body: RegisterParam) {
        _generic.value = Resource.loading()
        viewModelScope.launch {
            auth.register(body)
                .collectLatest { _generic.value = it }
        }
    }

    fun getUser() {
        _userData.value = Resource.loading()
        viewModelScope.launch {
            user.getPersonalInfo()
                .collectLatest { _userData.value = it }
        }
    }
}