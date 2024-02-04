package com.synrgy.kaboor.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.domain.user.UserUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 1/18/2024.
 * Github github.com/wahidabd.
 */


class SharedViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> get() = _login

    fun checkLogin() {
        viewModelScope.launch {
            userUseCase.getLogin()
                .collectLatest { state -> _login.value = state }
        }
    }

}