package com.synrgy.kaboor.ticket.plane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.synrgy.common.presentation.KaboorActivity
import com.synrgy.kaboor.databinding.ActivityFlightReturnTicketListBinding
import com.synrgy.kaboor.ticket.plane.adapter.PlaneTicketAdapter
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

    // TODO: For handle intent (Data, etc)
    override fun initIntent() {}

    // TODO: For UI
    override fun initUI() {
        initPlaneTicket()
    }

    // TODO: For Action (Click, Touch, etc)
    override fun initAction() {}

    // TODO: For Process (API, Call ViewModel, etc)
    override fun initProcess() {
        planeTicketAdapter.setData = ConstantDummy.planeTicket()
    }

    // TODO: For Observer (LiveData, etc)
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