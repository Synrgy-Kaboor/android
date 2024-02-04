package com.synrgy.kaboor.booking

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.constant.ConstantKey
import com.synrgy.common.utils.constant.ConstantTag
import com.synrgy.common.utils.enums.DetailPassengerType
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.flight.model.request.FlightParam
import com.synrgy.domain.flight.model.response.Flight
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.booking.dialog.DetailPassengerBottomSheetFragment
import com.synrgy.kaboor.databinding.ActivityPassengerDetailBinding
import com.wahidabd.library.utils.exts.onClick

class PassengerDetailActivity : KaboorActivity<ActivityPassengerDetailBinding>() {

    companion object {
        fun start(
            context: Context,
            departureFlightParam: Flight?,
            returnFlightParam: Flight? = null,
            flightParam: FlightParam?
        ) {
            context.startActivity(Intent(context, PassengerDetailActivity::class.java).apply {
                putExtra(ConstantKey.KEY_DEPARTURE_FLIGHT, departureFlightParam)
                putExtra(ConstantKey.KEY_RETURN_FLIGHT, returnFlightParam)
                putExtra(ConstantKey.KEY_FLIGHT_PARAM, flightParam)
            })
        }
    }

    private var flightParam: FlightParam? = null
    private var departureFlight: Flight? = null
    private var returnFlight: Flight? = null
    private var listFlight: MutableList<Flight> = mutableListOf()

    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(
            this,
            onClick = {}
        )
    }

    override fun getViewBinding(): ActivityPassengerDetailBinding =
        ActivityPassengerDetailBinding.inflate(layoutInflater)

    override fun initUI() {
        initPlaneTicket()
    }

    override fun initIntent() {
        departureFlight = intent.getParcelableExtra(ConstantKey.KEY_DEPARTURE_FLIGHT)
        returnFlight = intent.getParcelableExtra(ConstantKey.KEY_RETURN_FLIGHT)
        flightParam = intent.getParcelableExtra(ConstantKey.KEY_FLIGHT_PARAM)

        departureFlight?.let { listFlight.add(it) }
        returnFlight?.let { listFlight.add(it) }
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
        cvBookerDetail.onClick { showDetailInfo(DetailPassengerType.BOOKER) }
        ivEdit.onClick { if (!btnSwitch.isChecked) showDetailInfo(DetailPassengerType.PASSENGER) }
        btnOrder.onClick { ExtraProtectionActivity.start(this@PassengerDetailActivity) }
    }

    override fun initProcess() {
        planeTicketAdapter.setData = listFlight
    }

    private fun showDetailInfo(type: DetailPassengerType) {
        DetailPassengerBottomSheetFragment.newInstance(type)
            .show(supportFragmentManager, ConstantTag.TAG_DETAIL_PASSENGER)
    }

    private fun initPlaneTicket() = with(binding) {
        val layoutManager =
            LinearLayoutManager(
                this@PassengerDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        rvTicket.layoutManager = layoutManager
        rvTicket.adapter = planeTicketAdapter
    }
}