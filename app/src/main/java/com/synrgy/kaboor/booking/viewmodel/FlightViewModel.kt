package com.synrgy.kaboor.booking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synrgy.domain.flight.FlightUseCase
import com.synrgy.domain.flight.model.response.Airport
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


class FlightViewModel(
    private val flightUseCase: FlightUseCase
) : ViewModel() {

    private val _airports = MutableLiveData<Resource<List<Airport>>>()
    val airports: LiveData<Resource<List<Airport>>> = _airports

    fun getAirports() {
        viewModelScope.launch {
            flightUseCase.getAirports()
                .collectLatest {
                    _airports.postValue(it)
                }
        }
    }
}