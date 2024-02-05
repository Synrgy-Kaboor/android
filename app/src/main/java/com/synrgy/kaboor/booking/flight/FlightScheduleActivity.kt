package com.synrgy.kaboor.booking.flight

import android.content.Context
import android.content.Intent
import com.synrgy.common.model.AirportData
import com.synrgy.common.model.PassengerData
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.AirportType
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.oneDayMillis
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.snackbarDanger
import com.synrgy.common.utils.ext.timeNow
import com.synrgy.common.utils.ext.toDateFormatMonth
import com.synrgy.common.utils.ext.toGreeting
import com.synrgy.domain.flight.mapper.toData
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.kaboor.R
import com.synrgy.kaboor.booking.dialog.AirportBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.FlightClassBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.PassengerBottomSheetFragment
import com.synrgy.kaboor.booking.viewmodel.FlightViewModel
import com.synrgy.kaboor.booking.viewmodel.PassengerViewModel
import com.synrgy.kaboor.databinding.ActivityFlightScheduleBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject
import com.synrgy.common.R as comR

class FlightScheduleActivity : KaboorActivity<ActivityFlightScheduleBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FlightScheduleActivity::class.java))
        }
    }

    private val flightViewModel: FlightViewModel by inject()
    private val passengerViewModel: PassengerViewModel by inject()

    private var airports = mutableListOf<AirportData>()
    private var passengerData = PassengerData()
    private var planeClassType: PlaneClassType = PlaneClassType.EKONOMI

    override fun getViewBinding(): ActivityFlightScheduleBinding =
        ActivityFlightScheduleBinding.inflate(layoutInflater)

    override fun initUI() = with(binding) {
        tvPassenger.text = getString(comR.string.format_passenger_count, passengerData.count())
        tvClass.text = planeClassType.label
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

    override fun initProcess() {
        super.initProcess()
        flightViewModel.getAirports()
        passengerViewModel.getUser()
    }

    override fun initObservers() {
        super.initObservers()
        passengerViewModel.user.observe(this) {
            binding.tvUserName.text = it.fullName?.toGreeting()
        }
        flightViewModel.airports.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = { res ->
                hideLoading()
                airports.addAll(res.map { it.toData() })
                setAirport()
            }
        )
    }

    private fun setAirport() = with(binding) {
        val departure = airports.find { it.code == ConstantKey.KEY_AIRPORT_SUB }
        val arrival = airports.find { it.code == ConstantKey.KEY_AIRPORT_CGK }

        kaboorFlight.setDeparture(departure)
        kaboorFlight.setArrival(arrival)
    }

    private fun handleNavigation() = with(binding) {
        if (kaboorFlight.departure == kaboorFlight.arrival) {
            snackbarDanger(getString(R.string.error_same_airport))
            return
        }

        val roundTrip = kaboorSchedule.getRoundTrip()
        val flightParam = FlightParam(
            originCity = kaboorFlight.departure?.code.orEmpty(),
            destinationCity = kaboorFlight.arrival?.code.orEmpty(),
            departureDate = kaboorSchedule.departure.toDateFormatMonth(),
            returnDate = if (roundTrip) kaboorSchedule.getComingHome()
                .toDateFormatMonth() else null,
            numOfKids = passengerData.kid,
            numOfBabies = passengerData.baby,
            numOfAdults = passengerData.mature,
            classCode = planeClassType.code,
            departureData = kaboorFlight.departure,
            arrivalData = kaboorFlight.arrival,
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
        AirportBottomSheetFragment.newInstance(airports) { airportData ->
            when (type) {
                AirportType.DEPARTURE -> binding.kaboorFlight.setDeparture(airportData)
                AirportType.ARRIVAL -> binding.kaboorFlight.setArrival(airportData)
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
        val startDate = when (type) {
            AirportType.DEPARTURE -> timeNow - oneDayMillis
            AirportType.ARRIVAL -> kaboorSchedule.departure + oneDayMillis
        }
        showDatePicker(startDate) { date ->
            when (type) {
                AirportType.DEPARTURE -> kaboorSchedule.setDeparture(date)
                AirportType.ARRIVAL -> kaboorSchedule.setComingHome(date)
            }
        }
    }

}