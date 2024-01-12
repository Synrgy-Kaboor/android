package com.synrgy.kaboor.ticket.plane

import android.content.Context
import android.content.Intent
import com.synrgy.common.R as comR
import com.synrgy.kaboor.R
import com.synrgy.common.model.AirportData
import com.synrgy.common.model.PassengerData
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.AirportType
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.kaboor.authentication.login.LoginActivity
import com.synrgy.kaboor.databinding.ActivityFlightScheduleBinding
import com.synrgy.kaboor.ticket.plane.dialog.AirportBottomSheetFragment
import com.synrgy.kaboor.ticket.plane.dialog.FlightClassBottomSheetFragment
import com.synrgy.kaboor.ticket.plane.dialog.PassengerBottomSheetFragment
import com.wahidabd.library.utils.exts.onClick

class FlightScheduleActivity : KaboorActivity<ActivityFlightScheduleBinding>() {

    private var passengerData = PassengerData()
    private var departure: AirportData? = null
    private var arrival: AirportData? = null
    private var planeClassType: PlaneClassType = PlaneClassType.EKONOMI

    // TODO: Remove this after preference manager ready
    private var tempLogin = false


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FlightScheduleActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityFlightScheduleBinding =
        ActivityFlightScheduleBinding.inflate(layoutInflater)

    override fun initUI() = with(binding) {
        tvPassenger.text = getString(comR.string.format_passenger_count, passengerData.count)
        tvClass.text = planeClassType.label
    }

    override fun initAction() = with(binding) {
        cardPassenger.onClick { showPassengerDialog() }
        kaboorFlight.setOnDepartureListener { showAirportDialog(AirportType.DEPARTURE) }
        kaboorFlight.setOnArrivalListener { showAirportDialog(AirportType.ARRIVAL) }
        kaboorSchedule.setOnComingHomeListener { showDatePicker(AirportType.ARRIVAL) }
        kaboorSchedule.setOnDepartureListener { showDatePicker(AirportType.DEPARTURE) }
        cardClass.onClick { showPlaneClassDialog() }
        btnSubmit.onClick { handleNavigation() }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    private fun handleNavigation() {
        if (tempLogin) PassengerDetailActivity.start(this)
        else showLoginDialog(
            title = getString(R.string.message_login_required),
            description = getString(R.string.message_login_description),
            secondaryTextButton = getString(R.string.label_later),
            primaryTextButton = getString(R.string.label_login),
            primaryAction = { LoginActivity.start(this) }
        )
    }

    private fun showPassengerDialog() {
        PassengerBottomSheetFragment.newInstance(
            passenger = passengerData,
            onSave = { passenger ->
                passenger?.let {
                    passengerData = passenger
                    binding.tvPassenger.text = getString(comR.string.format_passenger_count, passengerData.count)
                }
            }
        ).show(supportFragmentManager, PassengerBottomSheetFragment::class.java.name)
    }

    private fun showAirportDialog(type: AirportType) {
        AirportBottomSheetFragment.newInstance { airportData ->
            when (type) {
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

    private fun showPlaneClassDialog() {
        FlightClassBottomSheetFragment.newInstance(defaultItem = planeClassType) { type ->
            planeClassType = type
            binding.tvClass.text = type.label
        }.show(supportFragmentManager, FlightClassBottomSheetFragment::class.java.name)
    }

    private fun showDatePicker(type: AirportType) = with(binding) {
        showDatePicker { date ->
            when (type) {
                AirportType.DEPARTURE -> kaboorSchedule.setDeparture(date)
                AirportType.ARRIVAL -> kaboorSchedule.setComingHome(date)
            }
        }
    }

}