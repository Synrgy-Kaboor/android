package com.synrgy.kaboor.booking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.model.response.User
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/4/2024.
 * Github github.com/wahidabd.
 */


class PassengerViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun getUser() {
        viewModelScope.launch {
            userUseCase.getUser().collectLatest {
                _user.value = it
            }
        }
    }

}