package com.synrgy.kaboor.booking.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.common.utils.ext.onBackPress
import com.synrgy.kaboor.databinding.ActivityFlightReturnTicketListBinding
import com.synrgy.kaboor.booking.adapter.PlaneTicketAdapter
import com.synrgy.kaboor.utils.constant.ConstantDummy

class FlightReturnTicketListActivity :
    KaboorActivity<ActivityFlightReturnTicketListBinding>() {

    companion object {
        fun start(context: AppCompatActivity) {
            context.startActivity(Intent(context, FlightReturnTicketListActivity::class.java))
        }
    }

    private val planeTicketAdapter by lazy {
        PlaneTicketAdapter(
            this,
            onClick = {}
        )
    }

    override fun getViewBinding(): ActivityFlightReturnTicketListBinding =
        ActivityFlightReturnTicketListBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        initPlaneTicket()
    }

    override fun initAction() = with(binding) {
        appbar.setOnBackClickListener { onBackPress() }
    }

    override fun initProcess() {
        planeTicketAdapter.setData = ConstantDummy.planeTicket()
    }

    override fun initObservers() {}

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