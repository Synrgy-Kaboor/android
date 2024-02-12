package com.synrgy.kaboor.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.notification.NotificationUseCase
import com.synrgy.domain.notification.model.request.PriceNotificationParam
import com.synrgy.domain.notification.model.response.Notification
import com.synrgy.domain.notification.model.response.PriceNotification
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/12/2024.
 * Github github.com/wahidabd.
 */


class NotificationViewModel(private val useCase: NotificationUseCase) : ViewModel() {

    private val _generic = MutableLiveData<Resource<KaboorResponse>>()
    val generic: LiveData<Resource<KaboorResponse>> = _generic

    private val _notification = MutableLiveData<Resource<List<Notification>>>()
    val notification: LiveData<Resource<List<Notification>>> = _notification

    private val _priceNotification = MutableLiveData<Resource<List<PriceNotification>>>()
    val priceNotification: LiveData<Resource<List<PriceNotification>>> = _priceNotification

    fun createPriceNotification(param: PriceNotificationParam) {
        _generic.value = Resource.Loading()
        viewModelScope.launch {
            useCase.createPriceNotification(param)
                .collectLatest { _generic.value = it }
        }
    }

    fun updatePriceNotification(id: Int, param: PriceNotificationParam){
        _generic.value = Resource.Loading()
        viewModelScope.launch {
            useCase.updatePriceNotification(id, param)
                .collectLatest { _generic.value = it }
        }
    }

    fun deletePriceNotification(id: Int){
        _generic.value = Resource.Loading()
        viewModelScope.launch {
            useCase.deletePriceNotification(id)
                .collectLatest { _generic.value = it }
        }
    }

    fun getPriceNotification(){
        _priceNotification.value = Resource.Loading()
        viewModelScope.launch {
            useCase.getPriceNotification()
                .collectLatest { _priceNotification.value = it }
        }
    }

    fun getNotification() {
        viewModelScope.launch {
            useCase.getNotification()
                .collectLatest { _notification.value = it }
        }
    }


}