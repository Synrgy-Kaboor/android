package com.synrgy.kaboor.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.domain.notification.NotificationUseCase
import com.synrgy.domain.notification.model.response.Notification
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/12/2024.
 * Github github.com/wahidabd.
 */


class NotificationViewModel(private val useCase: NotificationUseCase) : ViewModel() {

    private val _notification = MutableLiveData<Resource<List<Notification>>>()
    val notification: LiveData<Resource<List<Notification>>> = _notification

    fun getNotification() {
        viewModelScope.launch {
            useCase.getNotification()
                .collectLatest { _notification.value = it }
        }
    }

}