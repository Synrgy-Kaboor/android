package com.synrgy.kaboor.account.passport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.user.UserUseCase
import com.synrgy.domain.user.model.request.PassportParam
import com.synrgy.domain.user.model.response.Passport
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/14/2024.
 * Github github.com/wahidabd.
 */


class PassportViewModel(
    private val user: UserUseCase
) : ViewModel() {

    private val _generic = MutableLiveData<Resource<KaboorResponse>>()
    val generic: LiveData<Resource<KaboorResponse>> = _generic

    private val _passport = MutableLiveData<Resource<Passport>>()
    val passport: LiveData<Resource<Passport>> = _passport

    private val _passports = MutableLiveData<Resource<List<Passport>>>()
    val passports: LiveData<Resource<List<Passport>>> = _passports

    fun addPassport(param: PassportParam){
        _generic.value = Resource.Loading()
        viewModelScope.launch {
            user.addPassport(param).collectLatest {
                _generic.value = it
            }
        }
    }

    fun getPassports() {
        viewModelScope.launch {
            user.getAllPassport().collectLatest {
                _passports.value = it
            }
        }
    }

    fun updatePassport(id: String, param: PassportParam){
        viewModelScope.launch {
            user.updatePassport(id, param).collectLatest {
                _passport.value = it
            }
        }
    }

    fun deletePassport(id: String){
        _generic.value = Resource.Loading()
        viewModelScope.launch {
            user.deletePassport(id).collectLatest {
                _generic.value = it
            }
        }
    }
}