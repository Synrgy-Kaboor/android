package com.synrgy.kaboor.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.domain.order.OrderUseCase
import com.synrgy.domain.order.model.response.Order
import com.synrgy.domain.order.model.response.TicketDetail
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/8/2024.
 * Github github.com/wahidabd.
 */


class OrderViewModel(private val useCase: OrderUseCase) : ViewModel() {

    private val _order = MutableLiveData<Resource<List<Order>>>()
    val order: LiveData<Resource<List<Order>>> get() = _order

    private val _ticket = MutableLiveData<Resource<TicketDetail>>()
    val ticket: LiveData<Resource<TicketDetail>> get() = _ticket

    fun getActive() {
        viewModelScope.launch {
            useCase.getActive()
                .collectLatest {
                    _order.value = it
                }
        }
    }

    fun getFinished() {
        viewModelScope.launch {
            useCase.getFinished()
                .collectLatest {
                    _order.value = it
                }
        }
    }

    fun getOutbound(id: Int){
        viewModelScope.launch {
            useCase.getOutbound(id)
                .collectLatest {
                    _ticket.value = it
                }
        }
    }

    fun getReturn(id: Int){
        viewModelScope.launch {
            useCase.getReturn(id)
                .collectLatest {
                    _ticket.value = it
                }
        }
    }
}