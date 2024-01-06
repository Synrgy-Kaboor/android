package com.synrgy.kaboor.ticket.plane

import android.content.Context
import android.content.Intent
import com.synrgy.common.R
import com.synrgy.common.model.AirportData
import com.synrgy.common.model.PassengerData
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.AirportType
import com.synrgy.kaboor.databinding.ActivityFlightScheduleBinding
import com.synrgy.kaboor.ticket.plane.dialog.AirportBottomSheetFragment
import com.synrgy.kaboor.ticket.plane.dialog.PassengerBottomSheetFragment
import com.wahidabd.library.utils.exts.onClick

class FlightScheduleActivity : KaboorActivity<ActivityFlightScheduleBinding>() {

    private var passengerData = PassengerData()
    private var departure: AirportData? = null
    private var arrival: AirportData? = null

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FlightScheduleActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityFlightScheduleBinding =
        ActivityFlightScheduleBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() = with(binding) {
        tvPassenger.text = getString(R.string.format_passenger_count, passengerData.count)
    }

    override fun initAction() = with(binding) {
        cardPassenger.onClick { showPassengerDialog() }
        kaboorFlight.setOnDepartureListener { showAirportDialog(AirportType.DEPARTURE) }
        kaboorFlight.setOnArrivalListener { showAirportDialog(AirportType.ARRIVAL) }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    private fun showPassengerDialog() {
        PassengerBottomSheetFragment.newInstance(
            passenger = passengerData,
            onSave = { passenger ->
                passenger?.let {
                    passengerData = passenger
                    binding.tvPassenger.text =
                        getString(R.string.format_passenger_count, passengerData.count)
                }
            }
        ).show(supportFragmentManager, PassengerBottomSheetFragment::class.java.name)
    }

    private fun showAirportDialog(type: AirportType){
        AirportBottomSheetFragment.newInstance { airportData ->
            when(type){
                AirportType.DEPARTURE -> {
                    departure = airportData
                    binding.kaboorFlight.setDeparture(airportData)
                }
                AirportType.ARRIVAL -> {
                    arrival = airportData
                    binding.kaboorFlight.setArrival(airportData)
                }
            }
        }.show(supportFragmentManager, AirportBottomSheetFragment::class.java.name)
    }
}