package com.synrgy.kaboor.booking.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.common.utils.ext.toCurrency
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Flight
import com.synrgy.kaboor.booking.PassengerDetailActivity
import com.synrgy.kaboor.booking.PriceAlertActivity
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.databinding.ActivityFlightReturnTicketListBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy

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

    private var flightParam: FlightParam? = null
    private var departureFlight: Flight? = null

    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(
            this,
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
                flightParam?.originCity.toString(),
                flightParam?.destinationCity.toString()
            )
        )

        appbar.setDescription(
            date = flightParam?.departureDate.toString(),
            passenger = flightParam?.countPassenger() ?: 0,
            clazz = flightParam?.classCode.toString()
        )

        departureFlight?.image?.let { imgPlane.setImageResource(it) }
        tvPlane.text = departureFlight?.plane
        tvClass.text = departureFlight?.typeClass
        tvOrigin.text = departureFlight?.departure
        tvTakeOff.text = departureFlight?.departureTime
        tvDestination.text = departureFlight?.destination
        tvLanding.text = departureFlight?.destinationTime
        tvDuration.text = departureFlight?.boardingTime
        tvDate.text = departureFlight?.date
        tvPrice.text = departureFlight?.price?.toCurrency()

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
        planeTicketAdapter.setData = ConstantDummy.planeFlight()
    }

    override fun initObservers() {}

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