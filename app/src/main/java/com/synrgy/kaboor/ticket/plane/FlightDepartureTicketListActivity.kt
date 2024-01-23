package com.synrgy.kaboor.ticket.plane

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.domain.ticket.plane.Ticket
import com.synrgy.kaboor.databinding.ActivityFlightDepartureTicketListBinding
import com.synrgy.kaboor.ticket.plane.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy
import com.synrgy.kaboor.utils.constant.ConstantKey

class FlightDepartureTicketListActivity :
    KaboorActivity<ActivityFlightDepartureTicketListBinding>() {

    companion object {
        fun start(
            context: Context,
            isRoundTrip: Boolean
        ) {
            context.startActivity(
                Intent(
                    context,
                    FlightDepartureTicketListActivity::class.java
                ).apply {
                    putExtra(ConstantKey.KEY_ROUND_TRIP, isRoundTrip)
                }
            )
        }
    }

    private var isRoundTrip = false
    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(this, ::handleNavigation)
    }

    override fun getViewBinding(): ActivityFlightDepartureTicketListBinding =
        ActivityFlightDepartureTicketListBinding.inflate(layoutInflater)

    override fun initIntent() {
        isRoundTrip = intent.getBooleanExtra(ConstantKey.KEY_ROUND_TRIP, false)
    }

    override fun initUI() {
        initPlaneTicket()
    }

    override fun initAction() = with(binding){
        appbar.setOnBackClickListener { onBackPress() }
    }

    override fun initProcess() {
        planeTicketAdapter.setData = ConstantDummy.planeTicket()
    }

    override fun initObservers() {}

    private fun handleNavigation(ticket: Ticket) {
        if (isRoundTrip) FlightReturnTicketListActivity.start(this)
        else PassengerDetailActivity.start(this)
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