package com.synrgy.common.reusable.booking

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.synrgy.common.R
import com.synrgy.common.databinding.LayoutFlightCardBinding
import com.synrgy.common.model.AirportData
import com.synrgy.common.utils.ext.onGroupClick
import com.wahidabd.library.utils.exts.onClick


/**
 * Created by wahid on 1/3/2024.
 * Github github.com/wahidabd.
 */


class KaboorFlightCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var binding: LayoutFlightCardBinding

    var departure: AirportData? = null
        private set
    var arrival: AirportData? = null
        private set

    private var setOnDepartureListener: (() -> Unit)? = {}
    private var setOnArrivalListener: (() -> Unit)? = {}

    init {
        binding = LayoutFlightCardBinding.inflate(LayoutInflater.from(context), this)
        setupView()
    }

    private fun setupView() = with(binding) {
        tvDeparture.text =
            context.getString(R.string.format_airport, departure?.code, departure?.name)
        tvArrival.text = context.getString(R.string.format_airport, arrival?.code, arrival?.name)

        btnSwitch.onClick { switch() }
        groupDeparture.onGroupClick { setOnDepartureListener?.invoke() }
        groupArrival.onGroupClick { setOnArrivalListener?.invoke() }
    }

    private fun switch() {
        val temp = departure
        departure = arrival
        arrival = temp

        setDeparture(departure)
        setArrival(arrival)
    }

    fun setDeparture(departure: AirportData?) {
        this.departure = departure
        binding.tvDeparture.text =
            context.getString(R.string.format_airport, departure?.code, departure?.name)
    }

    fun setArrival(arrival: AirportData?) {
        this.arrival = arrival
        binding.tvArrival.text =
            context.getString(R.string.format_airport, arrival?.code, arrival?.name)
    }

    fun setOnDepartureListener(listener: () -> Unit) {
        setOnDepartureListener = listener
    }

    fun setOnArrivalListener(listener: () -> Unit) {
        setOnArrivalListener = listener
    }
}