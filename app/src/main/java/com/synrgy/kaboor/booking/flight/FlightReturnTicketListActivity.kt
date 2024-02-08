package com.synrgy.kaboor.booking.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.enums.PlaneClassType
import com.synrgy.common.utils.ext.calculatePlanePrice
import com.synrgy.common.utils.ext.convertToDuration
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.common.utils.ext.toGmtFormat
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Flight
import com.synrgy.kaboor.booking.PassengerDetailActivity
import com.synrgy.kaboor.booking.PriceAlertActivity
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.booking.viewmodel.FlightViewModel
import com.synrgy.kaboor.databinding.ActivityFlightReturnTicketListBinding
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.orZero
import com.wahidabd.library.utils.exts.setImageUrl
import org.koin.android.ext.android.inject

class FlightReturnTicketListActivity :
    KaboorActivity<ActivityFlightReturnTicketListBinding>() {

    companion object {
        fun start(
            context: AppCompatActivity,
            flightParam: FlightParam?,
            departureFlight: Flight
        ) {
            context.startActivity(
                Intent(
                    context,
                    FlightReturnTicketListActivity::class.java
                ).apply {
                    putExtra(ConstantKey.KEY_FLIGHT_PARAM, flightParam)
                    putExtra(ConstantKey.KEY_DEPARTURE_FLIGHT, departureFlight)
                })
        }
    }

    private val viewModel: FlightViewModel by inject()

    private var flightParam: FlightParam? = null
    private var departureFlight: Flight? = null

    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(
            this,
            flightParam,
            onClick = ::handleNavigation
        )
    }

    override fun getViewBinding(): ActivityFlightReturnTicketListBinding =
        ActivityFlightReturnTicketListBinding.inflate(layoutInflater)

    override fun initIntent() {
        flightParam = intent.getParcelableExtra(ConstantKey.KEY_FLIGHT_PARAM)
        departureFlight = intent.getParcelableExtra(ConstantKey.KEY_DEPARTURE_FLIGHT)
    }

    override fun initUI() = with(binding) {
        appbar.setTicketTitle(
            Pair(
                flightParam?.destinationCity.toString(),
                flightParam?.originCity.toString()
            )
        )

        appbar.setDescription(
            date = flightParam?.returnDate.toString(),
            passenger = flightParam?.countPassenger() ?: 0,
            clazz = flightParam?.classCode.toString()
        )

        setDepartureFlight()
        initPlaneTicket()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        appbar.setOnNotificationClickListener {
            PriceAlertActivity.start(
                this@FlightReturnTicketListActivity,
                flightParam
            )
        }
    }

    override fun initProcess() {
        val flightParam = flightParam?.copy(isReturn = true)
        flightParam?.let { viewModel.getFlight(it) }
    }

    override fun initObservers() {
        viewModel.flights.observerLiveData(
            this,
            onLoading = ::showLoading,
            onEmpty = {},
            onFailure = { _, message ->
                showErrorDialog(message.toString())
            },
            onSuccess = {
                hideLoading()
                planeTicketAdapter.setData = it
            }
        )
    }

    private fun setDepartureFlight() = with(binding) {
        val data = departureFlight
        imgPlane.setImageUrl(
            this@FlightReturnTicketListActivity,
            data?.plane?.airline?.imageUrl.toString()
        )
        tvPlane.text = data?.plane?.airline?.name
        tvOrigin.text = data?.originAirport?.code
        tvDestination.text = data?.destinationAirport?.code
        tvTakeOff.text = data?.originAirport?.timezone?.toGmtFormat(data.departureDatetime)
        tvDuration.text =
            convertToDuration(data?.departureDatetime.orEmpty(), data?.arrivalDatetime.orEmpty())
        tvLanding.text = data?.destinationAirport?.timezone?.toGmtFormat(data.arrivalDatetime)
        tvClass.text = PlaneClassType.getByCode(flightParam?.classCode).label

        val price = calculatePlanePrice(
            Pair(data?.adultPrice.orZero(), flightParam?.numOfAdults.orZero()),
            Pair(data?.childPrice.orZero(), flightParam?.numOfKids.orZero()),
            Pair(data?.babyPrice.orZero(), flightParam?.numOfBabies.orZero())
        )
        tvPrice.text = price.toCurrency()
    }

    private fun handleNavigation(flight: Flight) {
        PassengerDetailActivity.start(this, departureFlight, flight, flightParam)
    }

    private fun initPlaneTicket() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                this@FlightReturnTicketListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = planeTicketAdapter
    }
}