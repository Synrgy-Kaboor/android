package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import com.synrgy.common.R as comR
import com.synrgy.common.model.AirportData
import com.synrgy.common.model.PassengerData
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.enums.AirportType
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.oneWeekMillis
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.common.utils.ext.toEpochMillis
import com.synrgy.common.utils.ext.tomorrowMillis
import com.synrgy.domain.booking.model.request.FlightParam
import com.synrgy.kaboor.booking.dialog.AirportBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.FlightClassBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.PassengerBottomSheetFragment
import com.synrgy.kaboor.databinding.ActivityPriceAlertBinding
import com.synrgy.kaboor.utils.constant.ConstantKey
import com.synrgy.kaboor.utils.constant.ConstantTag
import com.wahidabd.library.utils.exts.onClick

class PriceAlertActivity : KaboorActivity<ActivityPriceAlertBinding>() {

    companion object {
        fun start(
            context: Context,
            flightParam: FlightParam?
        ) {
            context.startActivity(Intent(context, PriceAlertActivity::class.java).apply {
                putExtra(ConstantKey.KEY_FLIGHT_PARAM, flightParam)
            })
        }
    }

    private var flightParam: FlightParam? = null
    private var passengerData = PassengerData()
    private var departure: AirportData? = null
    private var arrival: AirportData? = null
    private var planeClassType: PlaneClassType = PlaneClassType.EKONOMI

    override fun getViewBinding(): ActivityPriceAlertBinding {
        return ActivityPriceAlertBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        super.initIntent()

        flightParam = intent.getParcelableExtra(ConstantKey.KEY_FLIGHT_PARAM)
        departure = flightParam?.departureData
        arrival = flightParam?.arrivalData
        planeClassType = PlaneClassType.getByCode(flightParam?.classCode)
        passengerData = PassengerData(
            mature = flightParam?.numOfAdults,
            kid = flightParam?.numOfKids,
            baby = flightParam?.numOfBabies
        )

    }

    override fun initUI() = with(binding) {
        tvPassenger.text = getString(comR.string.format_passenger_count, passengerData.count())
        tvClass.text = planeClassType.label

        kaboorFlight.setDeparture(departure)
        kaboorFlight.setArrival(arrival)
        kaboorSchedule.setRoundTrip(flightParam?.returnDate?.isNotEmpty() ?: false)
        kaboorSchedule.setDeparture(flightParam?.departureDate?.toEpochMillis() ?: tomorrowMillis)
        kaboorSchedule.setComingHome(flightParam?.returnDate?.toEpochMillis() ?: oneWeekMillis)

        rangeFormatSlider()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        cardPassenger.onClick { showPassengerDialog() }
        kaboorFlight.setOnArrivalListener { showAirportDialog(AirportType.ARRIVAL) }
        kaboorSchedule.setOnComingHomeListener { showDatePicker(AirportType.ARRIVAL) }
        kaboorSchedule.setOnDepartureListener { showDatePicker(AirportType.DEPARTURE) }
        kaboorFlight.setOnDepartureListener { showAirportDialog(AirportType.DEPARTURE) }
        cardClass.onClick { showPlaneClassDialog() }
    }

    private fun rangeFormatSlider() {
        binding.rangeSlider.setLabelFormatter { value ->
            value.toCurrency()
        }
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