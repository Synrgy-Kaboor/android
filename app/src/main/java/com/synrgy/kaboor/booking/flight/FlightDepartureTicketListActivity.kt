package com.synrgy.kaboor.booking.flight

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.kaboor.booking.PassengerDetailActivity
import com.synrgy.kaboor.booking.PriceAlertActivity
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.databinding.ActivityFlightDepartureTicketListBinding
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.domain.flight.model.response.Flight

class FlightDepartureTicketListActivity :
    KaboorActivity<ActivityFlightDepartureTicketListBinding>() {

    companion object {
        fun start(
            context: Context,
            isRoundTrip: Boolean,
            flightParam: FlightParam
        ) {
            context.startActivity(
                Intent(context, FlightDepartureTicketListActivity::class.java).apply {
                    putExtra(ConstantKey.KEY_ROUND_TRIP, isRoundTrip)
                    putExtra(ConstantKey.KEY_FLIGHT_PARAM, flightParam)
                }
            )
        }
    }

    private var flightParam: FlightParam? = null
    private var isRoundTrip = false
    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(this, ::handleNavigation)
    }

    override fun getViewBinding(): ActivityFlightDepartureTicketListBinding =
        ActivityFlightDepartureTicketListBinding.inflate(layoutInflater)

    override fun initIntent() {
        isRoundTrip = intent.getBooleanExtra(ConstantKey.KEY_ROUND_TRIP, false)
        flightParam = intent.getParcelableExtra(ConstantKey.KEY_FLIGHT_PARAM)
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

        initPlaneTicket()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        appbar.setOnNotificationClickListener {
            PriceAlertActivity.start(
                this@FlightDepartureTicketListActivity,
                flightParam
            )
        }
    }

    override fun initProcess() {
        planeTicketAdapter.setData = ConstantDummy.planeFlight()
    }

    override fun initObservers() {}

    private fun handleNavigation(flight: Flight) {
        if (isRoundTrip) FlightReturnTicketListActivity.start(this, flightParam, flight)
        else PassengerDetailActivity.start(this, flight, null, flightParam)
    }

    private fun initPlaneTicket() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                this@FlightDepartureTicketListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = planeTicketAdapter
    }
}