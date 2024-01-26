package com.synrgy.kaboor.booking.flight

import android.content.Context
import android.content.Intent
import com.synrgy.common.model.AirportData
import com.synrgy.common.model.PassengerData
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.AirportType
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.toDateFormatMonth
import com.synrgy.domain.booking.mapper.toData
import com.synrgy.domain.booking.model.request.FlightParam
import com.synrgy.kaboor.booking.dialog.AirportBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.FlightClassBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.PassengerBottomSheetFragment
import com.synrgy.kaboor.databinding.ActivityFlightScheduleBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.onClick
import com.synrgy.common.R as comR

class FlightScheduleActivity : KaboorActivity<ActivityFlightScheduleBinding>() {

    private var passengerData = PassengerData()
    private var departure: AirportData? = ConstantDummy.departure().toData()
    private var arrival: AirportData? = ConstantDummy.arrival().toData()
    private var planeClassType: PlaneClassType = PlaneClassType.EKONOMI


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FlightScheduleActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityFlightScheduleBinding =
        ActivityFlightScheduleBinding.inflate(layoutInflater)

    override fun initUI() = with(binding) {
        tvUserName.text = "Andre Hutshon"
        tvPassenger.text = getString(comR.string.format_passenger_count, passengerData.count())
        tvClass.text = planeClassType.label

        kaboorFlight.setDeparture(departure)
        kaboorFlight.setArrival(arrival)
    }

    override fun initAction() = with(binding) {
        kaboorFlight.setOnDepartureListener { showAirportDialog(AirportType.DEPARTURE) }
        kaboorFlight.setOnArrivalListener { showAirportDialog(AirportType.ARRIVAL) }
        kaboorSchedule.setOnComingHomeListener { showDatePicker(AirportType.ARRIVAL) }
        kaboorSchedule.setOnDepartureListener { showDatePicker(AirportType.DEPARTURE) }
        cardClass.onClick { showPlaneClassDialog() }
        cardPassenger.onClick { showPassengerDialog() }
        btnSubmit.onClick { handleNavigation() }
    }

    private fun handleNavigation() = with(binding) {
        val roundTrip = binding.kaboorSchedule.getRoundTrip()
        val flightParam = FlightParam(
            originCity = departure?.airport.orEmpty(),
            destinationCity = arrival?.airport.orEmpty(),
            departureDate = kaboorSchedule.departure.toDateFormatMonth(),
            returnDate = if (roundTrip) kaboorSchedule.getComingHome()
                .toDateFormatMonth() else null,
            numOfKids = passengerData.kid,
            numOfBabies = passengerData.baby,
            numOfAdults = passengerData.mature,
            classCode = planeClassType.code,
            departureData = departure,
            arrivalData = arrival,
        )

        FlightDepartureTicketListActivity.start(this@FlightScheduleActivity, roundTrip, flightParam)
    }

    private fun showPassengerDialog() {
        PassengerBottomSheetFragment.newInstance(
            passenger = passengerData,
            onSave = { passenger ->
                passenger?.let {
                    passengerData = passenger
                    binding.tvPassenger.text =
                        getString(comR.string.format_passenger_count, passengerData.count())
                }
            }
        ).show(supportFragmentManager, ConstantTag.TAG_PASSENGER)
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
        }.show(supportFragmentManager, ConstantTag.TAG_AIRPORT)
    }

    private fun showPlaneClassDialog() {
        FlightClassBottomSheetFragment.newInstance(defaultItem = planeClassType) { type ->
            planeClassType = type
            binding.tvClass.text = type.label
        }.show(supportFragmentManager, ConstantTag.TAG_PLANE_CLASS)
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