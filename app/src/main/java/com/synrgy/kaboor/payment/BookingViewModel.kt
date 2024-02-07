package com.synrgy.kaboor.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.common.data.response.KaboorResponse
import com.synrgy.domain.booking.BookingUseCase
import com.synrgy.domain.booking.model.request.BookingParam
import com.synrgy.domain.booking.model.request.ProofParam
import com.synrgy.domain.booking.model.request.UpdateProofParam
import com.synrgy.domain.booking.model.response.Booking
import com.synrgy.domain.booking.model.response.PaymentDetail
import com.synrgy.domain.booking.model.response.UploadProof
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/6/2024.
 * Github github.com/wahidabd.
 */


class BookingViewModel(private val useCase: BookingUseCase) : ViewModel() {

    private val _booking = MutableLiveData<Resource<Booking>>()
    val booking: LiveData<Resource<Booking>> = _booking

    private val _payment = MutableLiveData<Resource<PaymentDetail>>()
    val payment: LiveData<Resource<PaymentDetail>> = _payment

    private val _uploadProof = MutableLiveData<Resource<UploadProof>>()
    val uploadProof: LiveData<Resource<UploadProof>> = _uploadProof

    private val _generic = MutableLiveData<Resource<KaboorResponse>>()
    val generic: LiveData<Resource<KaboorResponse>> = _generic

    fun createBooking(body: BookingParam) {
        viewModelScope.launch {
            useCase.createBooking(body)
                .collectLatest { _booking.value = it }
        }
    }

    fun getPaymentDetail(id: Int) {
        viewModelScope.launch {
            useCase.getPaymentDetail(id)
                .collectLatest { _payment.value = it }
        }
    }

    fun uploadProof(body: ProofParam) {
        viewModelScope.launch {
            useCase.uploadProof(body)
                .collectLatest { _uploadProof.value = it }
        }
    }

    fun updateProof(id: Int, body: UpdateProofParam) {
        viewModelScope.launch {
            useCase.updateProof(id, body)
                .collectLatest { _generic.value = it }
        }
    }

}