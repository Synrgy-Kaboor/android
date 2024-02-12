package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import com.synrgy.common.model.AirportData
import com.synrgy.common.model.PassengerData
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.AirportType
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.enums.PriceAlertType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.oneDayMillis
import com.synrgy.common.utils.ext.showDatePicker
import com.synrgy.common.utils.ext.snackbarSuccess
import com.synrgy.common.utils.ext.timeNow
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.common.utils.ext.toDateFormatMonth
import com.synrgy.domain.flight.mapper.toData
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.notification.model.request.PriceNotificationParam
import com.synrgy.kaboor.booking.dialog.AirportBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.FlightClassBottomSheetFragment
import com.synrgy.kaboor.booking.dialog.PassengerBottomSheetFragment
import com.synrgy.kaboor.booking.viewmodel.FlightViewModel
import com.synrgy.kaboor.databinding.ActivityPriceAlertBinding
import com.synrgy.kaboor.notification.NotificationViewModel
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import org.koin.android.ext.android.inject
import com.synrgy.common.R as comR

class PriceAlertActivity : KaboorActivity<ActivityPriceAlertBinding>() {

    companion object {
        fun start(
            context: Context,
            flightParam: FlightParam?,
            type: PriceAlertType? = PriceAlertType.NEW,
            notificationId: Int? = 0
        ) {
            context.startActivity(Intent(context, PriceAlertActivity::class.java).apply {
                putExtra(ConstantKey.KEY_FLIGHT_PARAM, flightParam)
                putExtra(ConstantKey.KEY_PRICE_ALERT_TYPE, type)
                putExtra(ConstantKey.KEY_PRICE_ALERT_ID, notificationId)
            })
        }
    }

    private val flightViewModel: FlightViewModel by inject()
    private val notificationViewModel: NotificationViewModel by inject()

    private var airports = mutableListOf<AirportData>()

    private var flightParam: FlightParam? = null
    private var passengerData = PassengerData()
    private var departure: AirportData? = null
    private var arrival: AirportData? = null
    private var planeClassType: PlaneClassType = PlaneClassType.EKONOMI
    private var priceNotificationId = 0
    private var type = PriceAlertType.NEW

    override fun getViewBinding(): ActivityPriceAlertBinding {
        return ActivityPriceAlertBinding.inflate(layoutInflater)
    }

    override fun initIntent() {
        super.initIntent()

        type = intent.getSerializableExtra(ConstantKey.KEY_PRICE_ALERT_TYPE) as PriceAlertType
        priceNotificationId = intent.getIntExtra(ConstantKey.KEY_PRICE_ALERT_ID, 0)
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

        kaboorSchedule.disableRoundTrip()
        rangeFormatSlider()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        cardPassenger.onClick { showPassengerDialog() }
        kaboorSchedule.setOnDepartureListener { showDatePicker() }
        kaboorFlight.setOnArrivalListener { showAirportDialog(AirportType.ARRIVAL) }
        kaboorFlight.setOnDepartureListener { showAirportDialog(AirportType.DEPARTURE) }
        cardClass.onClick { showPlaneClassDialog() }
        btnSubmit.onClick { sendToObservable() }
    }

    override fun initProcess() {
        super.initProcess()
        flightViewModel.getAirports()
    }

    override fun initObservers() {
        super.initObservers()
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

        notificationViewModel.generic.observerLiveData(
            this,
            onLoading = ::showLoading,
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                snackbarSuccess(it.message)
                onBackPress()
            }
        )
    }

    private fun sendToObservable() = with(binding) {
        val param = PriceNotificationParam(
            totalAdults = passengerData.mature,
            totalChildren = passengerData.kid,
            totalBabies = passengerData.baby,
            classCode = planeClassType.code,
            minimumPrice = rangeSlider.values[0].toLong(),
            maximumPrice = rangeSlider.values[1].toLong(),
            date = kaboorSchedule.departure.toDateFormatMonth(),
            originAirportId = kaboorFlight.departure?.id ?: 0,
            destinationAirportId = kaboorFlight.arrival?.id ?: 0
        )

        if (type == PriceAlertType.NEW) notificationViewModel.createPriceNotification(param)
        else notificationViewModel.updatePriceNotification(priceNotificationId, param)
    }

    private fun setAirport() = with(binding) {
        val departure = airports.find { it.code == ConstantKey.KEY_AIRPORT_SUB }
        val arrival = airports.find { it.code == ConstantKey.KEY_AIRPORT_CGK }

        kaboorFlight.setDeparture(departure)
        kaboorFlight.setArrival(arrival)
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

    private fun showDatePicker() = with(binding) {
        showDatePicker(timeNow - oneDayMillis) {
            kaboorSchedule.setDeparture(it)
        }
    }
}