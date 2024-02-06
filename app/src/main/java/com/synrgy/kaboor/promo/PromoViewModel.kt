package com.synrgy.kaboor.promo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.domain.promo.PromoUseCase
import com.synrgy.domain.promo.model.response.Voucher
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


class PromoViewModel(private val useCase: PromoUseCase) : ViewModel() {

    private val _vouchers = MutableLiveData<Resource<List<Voucher>>>()
    val vouchers: LiveData<Resource<List<Voucher>>> get() = _vouchers

    fun getVouchers() {
        viewModelScope.launch {
            useCase.getVoucher()
                .collectLatest { _vouchers.value = it }
        }
    }
}