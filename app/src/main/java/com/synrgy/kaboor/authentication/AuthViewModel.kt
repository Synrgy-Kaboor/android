package com.synrgy.kaboor.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorGenericResponse
import com.synrgy.domain.auth.AuthUseCase
import com.synrgy.domain.auth.model.request.LoginParam
import com.synrgy.domain.auth.model.response.Login
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.model.response.User
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by wahid on 1/8/2024.
 * Github github.com/wahidabd.
 */


class AuthViewModel(
    private val auth: AuthUseCase,
    private val user: UserUseCase
) : ViewModel() {

    private val _userData = MutableLiveData<Resource<User>>()
    val userData: LiveData<Resource<User>> get() = _userData

    private val _jwt = MutableLiveData<Resource<Login>>()
    val jwt: LiveData<Resource<Login>> get() = _jwt

    private val _checkEmail = MutableLiveData<Resource<KaboorGenericResponse>>()
    val checkEmail: LiveData<Resource<KaboorGenericResponse>> get() = _checkEmail

    fun saveToken(token: String) {
        viewModelScope.launch {
            user.saveToken(token)
        }
    }

    fun checkEmail(email: String) {
        viewModelScope.launch {
            auth.checkEmail(email)
                .collectLatest { _checkEmail.value = it }
        }
    }

    fun login(data: LoginParam) {
        viewModelScope.launch {
            auth.login(data)
                .collectLatest { _jwt.value = it }
        }
    }
}